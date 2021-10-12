package com.example.myapplication.data.model.video

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "id")
    val id: String,
    @Json(name = "iso_639_1")
    val iso639: String,
    @Json(name = "iso_3166_1")
    val iso3166: String,
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "site")
    val site: String,
    @Json(name = "size")
    val size: Int,
    @Json(name = "type")
    val type: String
)
