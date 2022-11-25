package com.example.github.ui.fragments.searchfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.databinding.RepositoryItemBinding
import com.example.github.domain.models.Repository


class SearchFragmentAdapter(
    val downloadRepository: (title: String, description: String?, fullName: String, defaultBranch: String) -> Unit,
    val navigateToWebView: (url: String) -> Unit,
    val insertRepository: (id: Int, fullName: String) -> Unit
) : ListAdapter<Repository, SearchFragmentAdapter.SearchViewHolder>(SearchDiffUtils()) {


    inner class SearchViewHolder(
        private val binding: RepositoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(repository: Repository) {
            val (fullName, htmlUrl, id, name, url, description, defaultBranch) = repository
            with(binding) {
                this.entity = repository
                this.downloadReposButton.setOnClickListener {
                    downloadRepository(name, description, fullName, defaultBranch)
                    insertRepository(id,fullName)
                }
                this.reposCardView.setOnClickListener {
                    navigateToWebView(htmlUrl)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            RepositoryItemBinding.inflate(
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



