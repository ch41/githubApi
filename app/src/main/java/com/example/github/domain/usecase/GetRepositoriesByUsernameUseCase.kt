package com.example.github.domain.usecase

import com.example.github.domain.models.Repository
import com.example.github.domain.repository.GithubRemoteRepository
import com.example.github.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesByUsernameUseCase @Inject constructor(
    private val repository: GithubRemoteRepository
) {
    operator fun invoke(username:String): Flow<Resource<List<Repository>>> {
       return repository.getRepositoriesByUsername(username)
    }
}