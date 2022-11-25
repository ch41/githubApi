package com.example.github.domain.usecase

import com.example.github.domain.models.LocalRepository
import com.example.github.domain.repository.DownloadsLocalRepository
import javax.inject.Inject

class GetAllRepositoriesUseCase @Inject constructor(
    private val repository: DownloadsLocalRepository
) {
    suspend operator fun invoke() : List<LocalRepository> {
        return repository.getAllRepositories()
    }
}