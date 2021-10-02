package com.example.myapplication.data.model.entity

import androidx.room.*

@Entity(tableName = "remote_keys_popular")
data class PopularKey(
    @PrimaryKey(autoGenerate = true)
    val popularId: Int,
    val movieId: Int,
    val page: Int?
)