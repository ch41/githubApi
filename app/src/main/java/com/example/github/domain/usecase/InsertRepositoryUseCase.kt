package com.example.github.domain.usecase

import com.example.github.domain.models.LocalRepository
import com.example.github.domain.repository.DownloadsLocalRepository
import javax.inject.Inject

class InsertRepositoryUseCase @Inject constructor(
    private val downloadsLocalRepository: DownloadsLocalRepository
) {
    suspend operator fun invoke(localRepository: LocalRepository){
        downloadsLocalRepository.insertRepository(localRepository)
    }
}