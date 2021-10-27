package com.example.myapplication.data.model.response

import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie

data class Movies(
    val page: Int,
    val results: List<MovieDomain>,
    val totalPages: Int,
    val totalResults: Int,
)
