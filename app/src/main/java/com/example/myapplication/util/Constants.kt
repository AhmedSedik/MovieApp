package com.example.myapplication.util

/**
 * Created by Ahmad Sedeek on 9/18/2021.
 */
object Constants {

    const val API_VERSION: Int = 3
    const val BASE_URL= "https://api.themoviedb.org/"
    private const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"
    const val DATABASE_NAME = "movie_db"



    fun getPosterUrl(path: String?) = BASE_POSTER_URL + path

}