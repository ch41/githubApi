package com.example.github.data.repository

import com.example.github.data.local.DownloadDao
import com.example.github.data.mapper.toDomain
import com.example.github.data.mapper.toEntity
import com.example.github.domain.models.LocalRepository
import com.example.github.domain.repository.DownloadsLocalRepository
import javax.inject.Inject

class DownloadsLocalRepositoryImpl @Inject constructor(
    private val dao: DownloadDao
) : DownloadsLocalRepository {
    override suspend fun insertRepository(localRepository: LocalRepository) {
        dao.insertRepository(localRepository.toEntity())
    }

    override suspend fun getAllRepositories(): List<LocalRepository> {
        return dao.getAllRepositories().map {
            it.toDomain()
        }
    }
}