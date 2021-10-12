package com.example.myapplication.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id") val id: Any,
    @Json(name = "name") val name: String
)
