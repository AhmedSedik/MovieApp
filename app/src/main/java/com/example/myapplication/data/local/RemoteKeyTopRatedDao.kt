package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.entity.PopularKey
import com.example.myapplication.data.model.entity.TopRatedKey

@Dao
interface RemoteKeyTopRatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(popular: TopRatedKey)

    @Query("SELECT * FROM remote_keys_top ORDER BY topRatedId DESC LIMIT 1")
    suspend fun lastItemRemoteKey(): TopRatedKey?

    @Query("DELETE FROM remote_keys_top")
    suspend fun deleteAll()
}