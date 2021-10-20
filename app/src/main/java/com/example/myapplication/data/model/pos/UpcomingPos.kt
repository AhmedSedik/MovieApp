package com.example.movieapplication.models.pos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_pos")
data class UpcomingPos(
    @PrimaryKey(autoGenerate = true)
    val upcomingId: Long,
    val movieId: Long,
    val position: Int
)
