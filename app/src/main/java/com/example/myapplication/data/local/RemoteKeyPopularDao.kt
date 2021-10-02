package com.example.myapplication.data.local

import androidx.room.*
import com.example.myapplication.data.model.entity.PopularKey


@Dao
interface RemoteKeyPopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(popular: PopularKey)

    @Query("SELECT * FROM remote_keys_popular ORDER BY popularId DESC LIMIT 1")
    suspend fun lastItemRemoteKey(): PopularKey?

    @Query("DELETE FROM remote_keys_popular")
    suspend fun deleteAll()

}