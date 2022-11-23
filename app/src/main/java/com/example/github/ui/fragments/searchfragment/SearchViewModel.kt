package com.example.github.ui.fragments.searchfragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.domain.use_case.GetRepositoriesByUsernameUseCase
import com.example.github.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getRepositoriesByUsernameUseCase: GetRepositoriesByUsernameUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchFragmentState())
    val state: StateFlow<SearchFragmentState> get() = _state

    private var job: Job = SupervisorJob()

    fun getRepositories(username: String) {
        job.cancel()
        job = getRepositoriesByUsernameUseCase.invoke(username.trim()).onEach { resource ->
            when (resource) {
                is Resource.Loading -> _state.value = SearchFragmentState(isLoading = true)
                is Resource.Error -> _state.value =
                    SearchFragmentState(isError = resource.message ?: "An unexpected error", isLoading = false)
                is Resource.Success -> {
                    delay(5000)
                    _state.value = SearchFragmentState(resource.data!!, isLoading = false)
                    Log.d("asd", "getRepositories: ${resource.data} ")
                }
            }
        }.launchIn(viewModelScope)
    }

}