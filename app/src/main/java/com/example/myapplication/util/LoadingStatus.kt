package com.example.myapplication.util


enum class Status{
    LOADING,
    SUCCESS,
    ERROR
}

enum class ErrorCode {
    NO_DATA,
    NETWORK_ERROR,
    UNKNOWN_ERROR
}

data class LoadingStatus(val status : Status , val errorCode: ErrorCode?, val message: String?){
    companion object{
        fun loading(): LoadingStatus {
            return LoadingStatus(
                Status.LOADING,
                null,
                null
            )
        }
        fun success(errorCode : ErrorCode? = null, message: String? = null) : LoadingStatus {
            return LoadingStatus(
                Status.SUCCESS,
                errorCode,
                message
            )
        }
        fun error(errorCode : ErrorCode, message: String? = null) : LoadingStatus {
            return LoadingStatus(
                Status.ERROR,
                errorCode,
                message
            )
        }
    }
}