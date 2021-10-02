package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.entity.PopularKey
import com.example.myapplication.data.model.entity.UpcomingKey

@Dao
interface RemoteKeyUpcomingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(popular: UpcomingKey)


    @Query("SELECT * FROM remote_key_upcoming ORDER BY upcomingId DESC LIMIT 1")
    suspend fun lastItemRemoteKey(): UpcomingKey?

    @Query("DELETE FROM remote_key_upcoming")
    suspend fun deleteAll()
}