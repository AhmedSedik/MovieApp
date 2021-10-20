package com.example.myapplication.data.repository


import com.example.myapplication.data.model.domain.MovieDetailsDomain
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.data.remote.movies.MovieDetailsDto
import kotlinx.coroutines.flow.Flow

interface MovieCache {

    fun getMovies(page:Int?): Flow<List<MovieDomain>>
    fun getMovieById(movieId: Long): Flow<MovieDetailsDomain>

    suspend fun insert(movies: List<Movie>)
    suspend fun insertDetails(movie: MovieDetails)
    suspend fun deleteAll()
    suspend fun deleteDetails()
}