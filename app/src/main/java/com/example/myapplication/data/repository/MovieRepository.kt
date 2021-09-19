package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.remote.MovieServiceHelper

import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */

class MovieRepository @Inject constructor(private val movieService: MovieServiceHelper) :
    BaseRepository() {


    suspend fun getPopularMovies(page: Int, errorText: (String) -> Unit)
    : MutableLiveData<List<Movie>> {

        val popularMovies = movieService.getPopularMovies(page)
        return loadPageListCall(
            { popularMovies },
            MutableLiveData<List<Movie>>(),
            errorText
        )
    }

}