package com.example.myapplication.data.model.response

import com.example.myapplication.data.remote.movies.MovieDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @field:Json(name = "page")
    override var page: Int,
    @field:Json(name = "results")
    override val results: List<MovieDto>,
    @field:Json(name = "total_pages")
    val totalPages: Int,
    @field:Json(name = "total_results")
    val totalResults: Int

):BaseListResponse<MovieDto>{
    val nextPage: Int
        get() = page + 1
}
