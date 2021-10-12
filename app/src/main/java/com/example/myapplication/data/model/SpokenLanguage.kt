package com.example.myapplication.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
@JsonClass(generateAdapter = true)
data class SpokenLanguage(

    @Json(name = "iso_639_1")
    val iso6391: String,

    @Json(name = "name")
    val name: String
)
