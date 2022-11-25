package com.example.github.ui.fragments.downloadsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.github.databinding.FragmentDownloadsBinding
import com.example.github.ui.fragments.basefragment.BaseFragment
import com.example.github.ui.fragments.downloadsfragment.adapter.DownloadsFragmentAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadsFragment : BaseFragment<FragmentDownloadsBinding>() {

    private val viewModel: DownloadsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllRepositories()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.allReposState.collect {
                val adapter = DownloadsFragmentAdapter()
                adapter.submitList(it.repositories)
                binding.localRepositoriesRecyclerView.adapter = adapter
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDownloadsBinding = FragmentDownloadsBinding.inflate(inflater, container, false)


}