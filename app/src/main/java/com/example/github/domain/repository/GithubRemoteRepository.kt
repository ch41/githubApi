package com.example.github.domain.repository

import com.example.github.domain.models.Github
import com.example.github.domain.models.Repository
import com.example.github.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GithubRemoteRepository {
    fun getRepositoriesByUsername(username: String) : Flow<Resource<List<Repository>>>
}
