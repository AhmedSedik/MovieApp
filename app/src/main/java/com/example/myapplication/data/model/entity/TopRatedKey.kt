package com.example.myapplication.data.model.entity

import androidx.room.*

@Entity(tableName = "remote_keys_top")
data class TopRatedKey(
    @PrimaryKey(autoGenerate = true)
    val topRatedId: Int,
    val movieId: Int,
    val page: Int?
)