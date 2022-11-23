package com.example.github.data.repository

import com.example.github.data.mapper.toDomain
import com.example.github.data.remote.api.GithubService
import com.example.github.domain.models.Github
import com.example.github.domain.models.Repository
import com.example.github.domain.repository.GithubRemoteRepository
import com.example.github.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubRemoteRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRemoteRepository {
    override fun getRepositoriesByUsername(username: String): Flow<Resource<List<Repository>>> =
        flow {
            val result = githubService.getRepositoriesByUsername(username).map {
                it.toDomain()
            }
            emit(Resource.Loading())

            if (result.isEmpty()) emit(Resource.Error("There are no repositories"))
            else emit(Resource.Success(result))

        }.catch { error -> emit(Resource.Error(error.localizedMessage ?: "An unexpected error")) }
            .flowOn(
                Dispatchers.IO
            )

}