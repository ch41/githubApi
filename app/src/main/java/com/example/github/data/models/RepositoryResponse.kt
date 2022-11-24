package com.example.github.data.models

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("default_branch")
    val defaultBranch: String,
)