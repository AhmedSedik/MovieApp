package com.example.myapplication.data.model.response

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(val code: Int? = null, val error: Respo? = null) : Result<Nothing>()
    object NetworkError : Result<Nothing>()
}
