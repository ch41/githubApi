package com.example.github.domain.repository

import android.app.DownloadManager
import com.example.github.domain.models.Repository
import com.example.github.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GithubRemoteRepository {
    fun getRepositoriesByUsername(username: String): Flow<Resource<List<Repository>>>
    fun downloadRepository(
        downloadManager: DownloadManager,
        title: String,
        description: String?,
        uri: String
    ): Flow<Resource<Boolean>>
}
