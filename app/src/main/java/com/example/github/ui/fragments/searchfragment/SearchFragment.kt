package com.example.github.ui.fragments.searchfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.github.databinding.FragmentSearchBinding
import com.example.github.ui.fragments.basefragment.BaseFragment
import com.example.github.ui.fragments.searchfragment.adapter.SearchFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRepositories("ch41")
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                updateUi(state)
                Log.d("das", "onViewCreated: ${state.isLoading} ")
            }
        }

    }

    private fun updateUi(viewState: SearchFragmentState) {
        val (repositories, isLoading, isError) = viewState
        binding.isLoadingProgressBar.isVisible = isLoading
        if(isError.isNotEmpty()) Toast.makeText(requireContext(), isError, Toast.LENGTH_SHORT).show()
        val adapter = SearchFragmentAdapter()
        adapter.submitList(repositories)
        binding.repositoriesRecyclerView.adapter = adapter
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater, container, false)


}