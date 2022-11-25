package com.example.github.ui.fragments.downloadsfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.databinding.LocalRepositoryItemBinding
import com.example.github.domain.models.LocalRepository

class DownloadsFragmentAdapter :
    ListAdapter<LocalRepository, DownloadsFragmentAdapter.SearchViewHolder>(DownloadsDiffUtils()) {
    inner class SearchViewHolder(
        private val binding: LocalRepositoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repository: LocalRepository) {
            with(binding) {
                this.entity = repository
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LocalRepositoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
