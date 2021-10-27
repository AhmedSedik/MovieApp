package com.example.myapplication.data.remote.jsonAdapter

import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.response.MovieResponse
import com.example.myapplication.data.model.response.Movies
import com.squareup.moshi.FromJson

class MoviesJsonAdapter {

    @FromJson
    fun fromJson(moviesJson: MovieResponse?): Movies? {
        val results = moviesJson?.results
        if (results.isNullOrEmpty()) return null
        if (moviesJson.page == null) return null
        if (moviesJson.totalPages == null) return null
        if (moviesJson.totalResults == null) return null

        return Movies(
            moviesJson.page,
            moviesJson.results.map {
                MovieDomain(
                    it.adult ?: false,
                    it.backdropPath,
                    it.id ?: return null,
                    it.originalLanguage.orEmpty(),
                    it.originalTitle.orEmpty(),
                    it.overview ?: return null,
                    it.popularity ?: 0.0,
                    it.posterPath,
                    it.releaseDate.orEmpty(),
                    it.title ?: return null,
                    it.video ?: false,
                    it.voteAverage ?: 0.0,
                    it.voteCount ?: 0,
                )
            },
            moviesJson.totalPages,
            moviesJson.totalResults,
        )
    }
}