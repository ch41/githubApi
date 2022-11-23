package com.example.github.ui.fragments.searchfragment.adapter

import com.example.github.domain.models.Repository


import androidx.recyclerview.widget.DiffUtil

class SearchDiffUtils : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}