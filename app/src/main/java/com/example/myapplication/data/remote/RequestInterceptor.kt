package com.example.myapplication.data.remote

import com.example.myapplication.BuildConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.OkHttpClient

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */


internal class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url
        val url = originalUrl.newBuilder()
            .addQueryParameter("language", "en-US")
            .addQueryParameter("api_key", API_KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}