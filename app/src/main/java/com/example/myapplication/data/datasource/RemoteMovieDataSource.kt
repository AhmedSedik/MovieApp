package com.example.myapplication.data.datasource


import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails

import com.example.myapplication.data.remote.MovieService

import com.example.myapplication.data.repository.RemoteMovieRepository
import com.example.myapplication.data.toEntity
import timber.log.Timber


import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
class RemoteMovieDataSource @Inject constructor(private val movieService: MovieService) :
    RemoteMovieRepository {
    override suspend fun getMovies(page: Int?): List<Movie> {
        return movieService.getPopularMovies(page).body()?.results!!.toEntity()
    }

    override suspend fun getMovieById(movieId: Long): MovieDetails? {
        val result = movieService.getMovieById(movieId,
            "recommendations,cast, credits").body()

        Timber.d("Movies Details Result: ${result.toString()}")
        return result
    }



}