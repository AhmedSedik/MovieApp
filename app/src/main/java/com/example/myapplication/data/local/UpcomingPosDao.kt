package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.movieapplication.models.pos.UpcomingPos

@Dao
interface UpcomingPosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<UpcomingPos>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(upcomingPos: UpcomingPos)

    @Query("DELETE FROM upcoming_pos")
    suspend fun deleteAll()
}