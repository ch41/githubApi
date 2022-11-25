package com.example.github.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [DownloadEntity::class],
    version = 1
)
abstract class DownloadDatabase : RoomDatabase(){
    abstract val dao:DownloadDao
}