package com.example.myapplication.data.remote

import com.example.myapplication.util.Constants.API_VERSION
import com.example.myapplication.BuildConfig.API_KEY
import com.example.myapplication.data.model.response.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Ahmad Sedeek on 9/18/2021.
 */
interface MovieService {

    @GET("$API_VERSION/movie/popular")
    fun getPopularMovies(
        @Query("page") page: Int
    ): Call<MovieResponse>


    @GET("$API_VERSION/discover/movie")
    fun getMoviesList(
        @Query("page") page: Int
    ): Call<MovieResponse>

}