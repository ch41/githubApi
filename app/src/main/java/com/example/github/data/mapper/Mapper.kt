package com.example.github.data.mapper

import com.example.github.data.models.GithubResponse
import com.example.github.data.models.RepositoryResponse
import com.example.github.domain.models.Github
import com.example.github.domain.models.Repository


fun GithubResponse.toDomain(): Github {
    val repositoriesList = this.map { repositoryResponse -> repositoryResponse  }
    return Github(repositoriesList.map { repositoryResponse ->
        repositoryResponse.toDomain()
    })
}

fun RepositoryResponse.toDomain(): Repository =
    Repository(fullName, htmlUrl, id, name, url)

