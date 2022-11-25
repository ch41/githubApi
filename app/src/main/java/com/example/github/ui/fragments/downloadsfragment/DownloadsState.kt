package com.example.github.ui.fragments.downloadsfragment

import com.example.github.domain.models.LocalRepository
import com.example.github.domain.models.Repository

data class DownloadsState(
    val repositories:List<LocalRepository> = emptyList(),
    val isLoading:Boolean = false,
)