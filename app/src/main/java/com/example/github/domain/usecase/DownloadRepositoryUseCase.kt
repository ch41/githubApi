package com.example.github.domain.usecase

import android.app.DownloadManager
import com.example.github.domain.repository.GithubRemoteRepository
import com.example.github.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DownloadRepositoryUseCase @Inject constructor(
    private val repository: GithubRemoteRepository
) {
    operator fun invoke(
        downloadManager: DownloadManager,
        title: String,
        description: String?,
        uri: String
    ): Flow<Resource<Boolean>>  {
        return repository.downloadRepository(downloadManager, title, description, uri)
    }

}