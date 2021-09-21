package com.example.myapplication.util

import dagger.assisted.AssistedFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/20/2021.
 */

enum class MovieListType {

    POPULAR {
        override fun toString() = "Popular"
    },
    UPCOMING {
        override fun toString() = "Upcoming"
    },
    IN_THEATERS {
        override fun toString() = "In Theaters"
    }
}