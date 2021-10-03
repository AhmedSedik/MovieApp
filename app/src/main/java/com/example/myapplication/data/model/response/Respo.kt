package com.example.myapplication.data.model.response

import com.squareup.moshi.Json

data class Respo(

    @Json(name = "status_message")
    val errorMessage: String?,

    @Json(name = "status_code")
    val errorCode: Int?
)