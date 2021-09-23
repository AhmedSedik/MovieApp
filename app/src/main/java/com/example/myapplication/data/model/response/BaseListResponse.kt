package com.example.myapplication.data.model.response
/*
base interface for default api response for specific data classes
 */
interface BaseListResponse<T> {
        var page: Int
        val results: List<T>
}