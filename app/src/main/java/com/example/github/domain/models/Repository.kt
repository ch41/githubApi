package com.example.github.domain.models

data class Repository(
    val fullName: String,
    val htmlUrl: String,
    val id: Int,
    val name: String,
    val url: String,
    val description:String?,
    val defaultBranch:String
)
