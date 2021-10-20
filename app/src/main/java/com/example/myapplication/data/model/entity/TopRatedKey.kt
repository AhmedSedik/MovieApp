package com.example.myapplication.data.model.entity

import androidx.room.*

@Entity(tableName = "remote_keys_top")
data class TopRatedKey(
    @PrimaryKey(autoGenerate = true)
    val topRatedId: Long,
    val movieId: Long,
    val page: Int?
)