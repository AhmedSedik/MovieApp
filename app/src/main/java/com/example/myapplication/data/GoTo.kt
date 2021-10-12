package com.example.myapplication.data

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.remote.movies.MovieDto


interface GoToMovie {
    val goToMovieDetailsEvent: MutableLiveData<Event<MovieDto>>

    fun goTo(movie: MovieDto) {
        goToMovieDetailsEvent.value = Event(movie)
    }
}


