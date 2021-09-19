package com.example.myapplication.data.remote

import com.example.myapplication.data.model.response.MovieResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */
class MovieServiceHelperImpl @Inject constructor(
    private val movieService: MovieService
):MovieServiceHelper {


    override suspend fun getPopularMovies(part: Int): Call<MovieResponse> =
      movieService.getPopularMovies(part)


}