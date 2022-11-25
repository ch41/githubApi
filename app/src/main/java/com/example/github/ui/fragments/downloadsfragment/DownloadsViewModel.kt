package com.example.github.ui.fragments.downloadsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.models.LocalRepository
import com.example.github.domain.usecase.GetAllRepositoriesUseCase
import com.example.github.domain.usecase.InsertRepositoryUseCase
import com.example.github.ui.fragments.searchfragment.RepositoriesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadsViewModel @Inject constructor(
    private val getAllRepos: GetAllRepositoriesUseCase
) : ViewModel() {

    private val _allReposState= MutableStateFlow(DownloadsState())
    val allReposState: StateFlow<DownloadsState> get() = _allReposState

     fun getAllRepositories(){
        viewModelScope.launch {
            _allReposState.value = DownloadsState(isLoading = true)
            _allReposState.value = DownloadsState(repositories = getAllRepos())
        }
    }

}