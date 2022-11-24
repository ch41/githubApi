package com.example.github.data.repository

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import com.example.github.data.mapper.toDomain
import com.example.github.data.remote.api.GithubService
import com.example.github.domain.models.Repository
import com.example.github.domain.repository.GithubRemoteRepository
import com.example.github.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import javax.inject.Inject

class GithubRemoteRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRemoteRepository {
    override fun getRepositoriesByUsername(username: String): Flow<Resource<List<Repository>>> =
        flow {
            emit(Resource.Loading())
            val result = githubService.getRepositoriesByUsername(username).map {
                it.toDomain()
            }

            if (result.isEmpty()) emit(Resource.Error("There are no repositories"))
            else emit(Resource.Success(result))

        }.catch { error -> emit(Resource.Error(error.localizedMessage ?: "An unexpected error")) }
            .flowOn(Dispatchers.IO)

   override fun downloadRepository(
        downloadManager: DownloadManager,
        title: String,
        description: String?,
        uri:String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading(true))
        val file =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        file.mkdirs()
        val destination = Uri.fromFile(File(file, "${title}.zip"))

        val dm =
            DownloadManager.Request(uri.toUri())
                .setTitle(title)
                .setDescription(description ?: "Downloading...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedOverMetered(true)
                .setDestinationUri(destination)

        downloadManager.enqueue(dm)

        emit(Resource.Success(true))

    }.catch { error -> emit(Resource.Error(error.localizedMessage ?: "An unexpected error")) }.flowOn(Dispatchers.IO)
}