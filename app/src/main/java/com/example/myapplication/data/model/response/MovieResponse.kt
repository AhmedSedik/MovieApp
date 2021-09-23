package com.example.myapplication.data.model.response

import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.response.BaseListResponse
import com.squareup.moshi.Json

data class MovieResponse(
    @Json(name = "page")
    override var page: Int,
    @Json(name = "results")
    override val results: List<Movie>

) : BaseListResponse<Movie>
