package com.example.myapplication.data.remote

import com.example.myapplication.data.model.response.MovieResponse
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */
interface MovieServiceHelper {
    suspend fun getPopularMovies(part:Int): Call<MovieResponse>
}