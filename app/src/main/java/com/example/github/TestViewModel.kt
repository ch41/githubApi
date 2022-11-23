package com.example.github

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.data.remote.api.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.file.Paths
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val githubService: GithubService
) : ViewModel() {
    fun test() {
        viewModelScope.launch {
           val repos =  githubService.getRepositoriesByUsername("Ch41")
        }
    }

    fun download(downloadManager: DownloadManager) {
        viewModelScope.launch {
            githubService.getRepositoriesByUsername("Ch41")

            val file =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

            file.mkdirs()
            val destination = Uri.fromFile(File(file, "pizda.zip"))
            val dm =
                DownloadManager.Request("https://codeload.github.com//ch41/BusinessCardApp/zip/master".toUri())
                    .setTitle("Pizda")
                    .setDescription("downloading...")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)
                    .setDestinationUri(destination)


            downloadManager.enqueue(dm)

        }
    }


}