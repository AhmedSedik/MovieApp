package com.example.myapplication.data.repository

import com.example.myapplication.data.model.entity.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteMovieRepository  {
     suspend fun getMovies(page: Int?): List<Movie>
}