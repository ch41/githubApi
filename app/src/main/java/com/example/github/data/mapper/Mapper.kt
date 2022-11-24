package com.example.github.data.mapper

import com.example.github.data.models.RepositoryResponse
import com.example.github.domain.models.Repository


fun RepositoryResponse.toDomain(): Repository =
    Repository(fullName, htmlUrl, id, name, url, description,defaultBranch)

