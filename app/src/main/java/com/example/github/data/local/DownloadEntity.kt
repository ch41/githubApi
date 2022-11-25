package com.example.github.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "download_entity")
data class DownloadEntity(
    @PrimaryKey
    val id: Int,
    val username:String,
    val repository: String
)