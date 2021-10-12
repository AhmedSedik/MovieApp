package com.example.myapplication.util

sealed class Resources<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resources<T>(data)
    class Loading<T>(data: T? = null) : Resources<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resources<T>(data, throwable)
}