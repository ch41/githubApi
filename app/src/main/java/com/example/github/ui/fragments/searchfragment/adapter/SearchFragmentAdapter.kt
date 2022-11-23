package com.example.github.ui.fragments.searchfragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.github.databinding.RepositoryItemBinding
import com.example.github.domain.models.Repository


class SearchFragmentAdapter(
//    val addCurrencyToFavorite: () -> Unit,
//    val deleteFromFavorites: () -> Unit
) : ListAdapter<Repository, SearchFragmentAdapter.SearchViewHolder>(SearchDiffUtils()) {


    inner class SearchViewHolder(
        private val binding: RepositoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(repository: Repository) {
            val (fullName, htmlUrl, id, name, url) = repository
            with(binding) {
                this.entity = repository
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



