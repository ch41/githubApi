package com.example.github.ui.fragments.searchfragment

import com.example.github.domain.models.LocalRepository
import com.example.github.domain.models.Repository

data class RepositoriesState(
    val repositories: List<Repository> = emptyList(),
    val isLoading:Boolean = false,
    val isError:String = "",
)
