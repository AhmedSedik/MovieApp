package com.example.myapplication.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key_upcoming")
data class UpcomingKey(
    @PrimaryKey(autoGenerate = true)
    val upcomingId: Int,
    val movieId: Int,
    val page: Int?
)
