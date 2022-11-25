package com.example.github.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DownloadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepository(downloadEntity: DownloadEntity)

    @Query("select * from download_entity")
    suspend fun getAllRepositories() : List<DownloadEntity>
}