package com.example.myapplication.data

import android.media.Image
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.remote.MovieDto


interface GoToMovie {
    val goToMovieDetailsEvent: MutableLiveData<Event<MovieDto>>

    fun goTo(movie: MovieDto) {
        goToMovieDetailsEvent.value = Event(movie)
    }
}


