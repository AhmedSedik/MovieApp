package com.example.myapplication.data.model.crew

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Crew(

    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "gender") val gender: Int?,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "known_for_department") val knownForDepartment: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "original_name") val originalName: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "profile_path") val profilePath: String?,
    @field:Json(name = "credit_id") val creditId: String,
    @field:Json(name = "department") val department: String,
    @field:Json(name = "job") val job: String,

)
