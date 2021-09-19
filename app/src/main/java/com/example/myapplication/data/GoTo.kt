package com.example.myapplication.data

import android.media.Image
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.entity.Movie




interface GoToMovie {
    val goToMovieDetailsEvent: MutableLiveData<Event<Movie>>

    fun goTo(movie: Movie) {
        goToMovieDetailsEvent.value = Event(movie)
    }
}


