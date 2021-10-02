package com.example.myapplication.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.myapplication.data.model.entity.Movie

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY api_page_index")
    fun getMoviesFlow(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movies")
    suspend fun getMovieList(): List<Movie>


    @Query("DELETE FROM movies")
    suspend fun deleteAllPopularMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Movie>)

    @Update
    suspend fun updateMovies(list: List<Movie>)
}