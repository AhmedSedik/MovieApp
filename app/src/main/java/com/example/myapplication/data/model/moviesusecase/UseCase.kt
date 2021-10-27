package com.example.myapplication.data.model.moviesusecase

interface UseCase<in Params, out T> {
    fun execute(params: Params): T
}