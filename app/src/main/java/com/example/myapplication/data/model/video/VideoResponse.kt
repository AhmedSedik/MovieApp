package com.example.myapplication.data.model.video

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
@JsonClass(generateAdapter = true)
data class VideoResponse(
    @Json(name = "results")
    val results: List<Video>
)
