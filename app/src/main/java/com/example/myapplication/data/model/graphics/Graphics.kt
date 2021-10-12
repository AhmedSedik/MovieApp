package com.example.myapplication.data.model.graphics

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
@JsonClass(generateAdapter = true)
data class Graphics(
    @Json(name = "id")
    val id: Int?,

    @Json(name = "backdrops")
    val backdrops: List<GraphicDetails>,

    @Json(name = "posters")
    val posters: List<GraphicDetails>
)
