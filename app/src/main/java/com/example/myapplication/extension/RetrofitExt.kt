package com.example.myapplication.extension


import com.example.myapplication.data.model.response.APIResponse

import retrofit2.Call
import retrofit2.Response
//https://medium.cobeisfresh.com/making-android-networking-pretty-with-kotlin-coroutines-b364ffbd803c
inline fun <T> Call<T>.request(crossinline onResult: (response: APIResponse<T>) -> Unit) {
    enqueue(object : retrofit2.Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onResult(APIResponse.Success(response))
            } else {
                onResult(APIResponse.Failure(response))
            }
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            onResult(APIResponse.Exception(throwable))
        }
    })
}
