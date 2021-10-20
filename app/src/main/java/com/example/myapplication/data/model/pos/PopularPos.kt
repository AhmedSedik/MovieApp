package com.example.movieapplication.models.pos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_pos")
data class PopularPos(
    @PrimaryKey(autoGenerate = true)
    val popularId: Long,
    val movieId: Long,
    val position: Int
)
