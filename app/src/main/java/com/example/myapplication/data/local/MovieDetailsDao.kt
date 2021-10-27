package com.example.myapplication.data.local

import androidx.room.*
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Created by Ahmad Sedeek on 10/12/2021.
 */
@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM movie_details WHERE id =:id")
    fun getMovieById(id: Long) : Flow<MovieDetails?>

    fun getMovieDetailsDistinctUntilChanged(id: Long) =
        getMovieById(id).distinctUntilChanged()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie:MovieDetails?)

    @Update
    suspend fun updateMovies(list: List<MovieDetails>)

    @Query("DELETE FROM movie_details")
    suspend fun deleteAllMovieDetails()

}