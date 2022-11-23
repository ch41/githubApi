package com.example.github.di

import com.example.github.data.remote.api.GithubService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGithubService() : GithubService = GithubService()
}