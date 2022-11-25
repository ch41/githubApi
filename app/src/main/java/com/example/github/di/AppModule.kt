package com.example.github.di

import android.content.Context
import androidx.room.Room
import com.example.github.data.local.DownloadDao
import com.example.github.data.local.DownloadDatabase
import com.example.github.data.remote.api.GithubService
import com.example.github.data.repository.DownloadsLocalRepositoryImpl
import com.example.github.data.repository.GithubRemoteRepositoryImpl
import com.example.github.domain.repository.DownloadsLocalRepository
import com.example.github.domain.repository.GithubRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGithubService(): GithubService = GithubService()

    @Provides
    fun provideGithubRepository(
        githubService: GithubService,
//        dispatchers: Dispatchers
    ): GithubRemoteRepository =
        GithubRemoteRepositoryImpl(githubService)

    @Provides
    fun provideDownloadsRepository(
        dao: DownloadDao
    ): DownloadsLocalRepository = DownloadsLocalRepositoryImpl(dao)

    @Provides
    fun provideDownloadsDao(database: DownloadDatabase): DownloadDao = database.dao

    @Provides
    fun provideDownloadsDatabase(@ApplicationContext app: Context): DownloadDatabase =
        Room.databaseBuilder(app, DownloadDatabase::class.java, "download_entity").build()
}