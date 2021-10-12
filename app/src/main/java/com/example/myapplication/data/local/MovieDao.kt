package com.example.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.example.myapplication.data.model.entity.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY api_page_index")
    fun getMoviesPaging(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movies ORDER BY api_page_index")
    fun getMoviesFlow(): Flow<List<Movie>>

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies")
    suspend fun getMovieList(): List<Movie>

    @Query("SELECT * FROM movies")
     fun getMovieListLiveData(): LiveData<List<Movie>>


    @Query("DELETE FROM movies")
    suspend fun deleteAllPopularMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Movie>)


    @Update
    suspend fun updateMovies(list: List<Movie>)
}