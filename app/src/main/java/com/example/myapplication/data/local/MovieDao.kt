package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.myapplication.data.model.entity.Movie

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getMovieList(): List<Movie>


}