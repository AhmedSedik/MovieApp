package com.example.myapplication.data.remote

import com.example.myapplication.data.model.response.MovieResponse
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */
class MovieServiceHelperImpl @Inject constructor(
    private val movieService: MovieService
):MovieServiceHelper {


    override suspend fun getPopularMovies(page: Int): Call<MovieResponse> =
      movieService.getPopularMovies(page)

    override suspend fun getMovieList(page: Int): Call<MovieResponse> =
        movieService.getMoviesList(page)


}