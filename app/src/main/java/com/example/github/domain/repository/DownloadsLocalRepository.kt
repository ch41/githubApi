package com.example.github.domain.repository

import com.example.github.domain.models.LocalRepository

interface DownloadsLocalRepository {
    suspend fun insertRepository(localRepository: LocalRepository)
    suspend fun getAllRepositories() : List<LocalRepository>
}