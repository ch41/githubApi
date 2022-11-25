package com.example.github.ui.fragments.downloadsfragment.adapter


import androidx.recyclerview.widget.DiffUtil
import com.example.github.domain.models.LocalRepository

class DownloadsDiffUtils : DiffUtil.ItemCallback<LocalRepository>() {
    override fun areItemsTheSame(oldItem: LocalRepository, newItem: LocalRepository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocalRepository, newItem: LocalRepository): Boolean {
        return oldItem == newItem
    }
}