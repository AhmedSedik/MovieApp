package com.example.myapplication.data.repository


import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieCache {

    fun getMovies(page:Int?): Flow<List<MovieDomain>>
    suspend fun insert(movies: List<Movie>)
    suspend fun deleteAll()
}