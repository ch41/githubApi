package com.example.github.ui.fragments.searchfragment

import android.app.DownloadManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.models.LocalRepository
import com.example.github.domain.models.Repository
import com.example.github.domain.usecase.DownloadRepositoryUseCase
import com.example.github.domain.usecase.GetRepositoriesByUsernameUseCase
import com.example.github.domain.usecase.InsertRepositoryUseCase
import com.example.github.ui.fragments.downloadsfragment.DownloadsState
import com.example.github.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRepositoriesByUsernameUseCase: GetRepositoriesByUsernameUseCase,
    private val downloadRepositoryUseCase: DownloadRepositoryUseCase,
    private val insertRepos: InsertRepositoryUseCase
) : ViewModel() {

    private val _repositoriesState = MutableStateFlow(RepositoriesState())
    val repositoriesState: StateFlow<RepositoriesState> get() = _repositoriesState

    private var job: Job = SupervisorJob()

    fun getRepositories(username: String) {
        job.cancel()
        job = getRepositoriesByUsernameUseCase(username.trim()).onEach { resource ->
            when (resource) {
                is Resource.Loading -> _repositoriesState.value =
                    RepositoriesState(isLoading = true)
                is Resource.Error -> _repositoriesState.value =
                    RepositoriesState(
                        isError = resource.message ?: "An unexpected error",
                        isLoading = false
                    )
                is Resource.Success -> _repositoriesState.value =
                    RepositoriesState(resource.data!!, isLoading = false)
            }
        }.launchIn(viewModelScope)
    }

    fun downloadRepository(
        downloadManager: DownloadManager,
        title: String,
        description: String?,
        fullName: String,
        defaultBranch: String
    ) {
        val uri = "https://codeload.github.com/$fullName/zip/$defaultBranch"
        downloadRepositoryUseCase(downloadManager, title, description, uri).launchIn(viewModelScope)
    }

    fun insertRepository(id: Int, fullName:String) {
        viewModelScope.launch {
            insertRepos(LocalRepository(id, fullName.substringBefore("/")  ,fullName.substringAfter("/")))
        }
    }
}