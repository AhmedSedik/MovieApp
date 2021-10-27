package com.example.myapplication.util

sealed class Resources<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resources<T>(data)
    class Loading<T>(data: T? = null) : Resources<T>(data)
    class Error<T>(throwable: Throwable?, data: T? = null) : Resources<T>(data, throwable)

    sealed class ErrorType(
        val throwable: Throwable? = null,
        val message: Int? = null
    ) {
        class DatabaseError(throwable: Throwable? = null) : ErrorType(throwable)
        class IOError(throwable: Throwable? = null) : ErrorType(throwable)
        class HttpError(throwable: Throwable? = null, val statusCode: Int) : ErrorType(throwable)
        class Unknown(throwable: Throwable? = null) : ErrorType(throwable)
    }
}