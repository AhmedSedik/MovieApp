package com.example.myapplication.ui


import com.example.myapplication.data.model.domain.MovieDomain

interface MovieClickListener {
    fun onClick(movieData: MovieDomain)
}