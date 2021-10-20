package com.example.myapplication.data.repository

import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.data.remote.movies.MovieDetailsDto
import kotlinx.coroutines.flow.Flow

interface RemoteMovieRepository  {
     suspend fun getMovies(page: Int?): List<Movie>
     suspend fun getMovieById(movieId: Long): MovieDetails

}