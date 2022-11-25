package com.example.github.ui.fragments.searchfragment

import android.app.DownloadManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.ui.fragments.basefragment.BaseFragment
import com.example.github.ui.fragments.searchfragment.adapter.SearchFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()
    private var downloadManager: DownloadManager? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        downloadManager =
            requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        binding.searchButton.setOnClickListener {
            viewModel.getRepositories(binding.usernameTextInput.text.toString())
            hideKeyboard()
        }

        binding.usernameTextInput.setOnEditorActionListener { textView, _, _ ->
            viewModel.getRepositories(textView.text.toString())
            hideKeyboard()
            true
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.repositoriesState.collect { state ->
                updateUi(state)
            }
        }
    }

    private fun updateUi(repositoriesState: RepositoriesState) {
        val (repositories, isLoading, isError) = repositoriesState
        binding.isLoadingProgressBar.isVisible = isLoading

        if (isError.isNotEmpty()) Toast.makeText(requireContext(), isError, Toast.LENGTH_SHORT)
            .show()

        val adapter = SearchFragmentAdapter(
            downloadRepository = { title, description, fullName, defaultBranch ->
                viewModel.downloadRepository(
                    downloadManager!!,
                    title,
                    description,
                    fullName,
                    defaultBranch
                )
            },
            navigateToWebView = { url ->
                val action = SearchFragmentDirections.actionSearchFragmentToWebViewFragment(url)
                findNavController().navigate(action)
            }
        )
        adapter.submitList(repositories)
        binding.repositoriesRecyclerView.adapter = adapter
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)


}