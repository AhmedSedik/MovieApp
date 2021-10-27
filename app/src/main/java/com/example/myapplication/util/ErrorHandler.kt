package com.example.myapplication.util

interface ErrorHandler {
    fun getError(throwable: Throwable): Resources.ErrorType
    fun getApiError(statusCode: Int, throwable: Throwable? = null): Resources.ErrorType
}