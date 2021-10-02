package com.example.myapplication.data.model.response

import com.example.myapplication.data.remote.MovieDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "page")
    var page: Int,
    @Json(name = "results")
    val results: List<MovieDto>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int

){
    val nextPage: Int
        get() = page + 1
}
