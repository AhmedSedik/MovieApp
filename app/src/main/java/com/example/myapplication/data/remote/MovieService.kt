package com.example.myapplication.data.remote

import com.example.myapplication.data.local.MovieDetailsDao
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.util.Constants.API_VERSION

import com.example.myapplication.data.model.response.MovieResponse
import com.example.myapplication.data.remote.movies.MovieDetailsDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ahmad Sedeek on 9/18/2021.
 */
interface MovieService {

    @GET("$API_VERSION/movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int?
    ): Response<MovieResponse>

    @GET("$API_VERSION/movie/popular")
    suspend fun getPopularMoviesRemote(
        @Query("page") page: Int?
    ): MovieResponse

    @GET("$API_VERSION/movie/popular")
     fun getPopularMoviesCall(
        @Query("page") page: Int?
    ): Call<MovieResponse>

    @GET("$API_VERSION/movie/top_rated")
    suspend fun getTopRatedMovie(
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("$API_VERSION/movie/now_playing")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int?
    ): Response<MovieResponse>


    @GET("$API_VERSION/discover/movie")
    suspend fun getMoviesList(
        @Query("page") page: Int?
    ): Response<MovieResponse>

    @GET("$API_VERSION/movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Long?,
        @Query("append_to_response") appendix: String,
        ): Response<MovieDetails>

}