package com.example.myapplication.data.model.crew

import com.squareup.moshi.Json

data class Crew(

    @Json(name = "id")
    val id: Int,

    @Json(name = "credit_id")
    val creditId: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "department")
    val department: String,

    @Json(name = "job")
    val job: String,

    @Json(name = "profile_path")
    val profilePath: String?,

    @Json(name = "gender")
    val gender: Int

)
