package com.example.myapplication.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Ahmad Sedeek on 9/25/2021.
 */
@JsonClass(generateAdapter = true)
data class MovieDto(

    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String?,
    /*@Json(name = "genre_ids")
    val genreIds: List<Int?>*/
    /*@Json(name = "backdrop_path")
    val backDropPath: String?,*/
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
   /* @Json(name = "vote_count")
    val voteCount: Int?,*/
    @Json(name = "release_date")
    val releaseDate: String?,
    /*@Json(name = "original_language")
    val originalLanguage: String?,*/
    @Json(name = "overview")
    val overview: String?,

)