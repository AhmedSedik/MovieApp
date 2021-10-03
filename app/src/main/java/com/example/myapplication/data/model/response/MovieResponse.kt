package com.example.myapplication.data.model.response

import com.example.myapplication.data.remote.MovieDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "page")
    override var page: Int,
    @Json(name = "results")
    override val results: List<MovieDto>,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int

):BaseListResponse<MovieDto>{
    val nextPage: Int
        get() = page + 1
}
