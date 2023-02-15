package com.example.myapplication.data.remote.jsonAdapter

import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.data.remote.Genre
import com.example.myapplication.data.remote.movies.MovieDetailsDto
import com.squareup.moshi.FromJson
import java.util.*

class MovieDetailsJsonAdapter {

    @FromJson
    fun fromJson(moviesJson: MovieDetailsDto): MovieDetails? {
        if (moviesJson.id == null) return null
        if (moviesJson.title == null) return null
        if (moviesJson.overview == null) return null

        val entity = with(moviesJson) {
            MovieDetails(
                id!!,
                overview!!,
                posterPath,
                releaseDate.orEmpty(),
                title!!,
                genres?.mapNotNull { it.asDbModel() } ?: emptyList(),
//                System.currentTimeMillis(),
                popularity ?: 0.0,
                voteAverage ?: 0.0,
                voteCount ?: 0,
                status.orEmpty(),
                recommendations.results?.mapNotNull {
                   Movie(
                        it.adult ?: false,
                        it.backdropPath,
                        it.id ?: return null,
                        it.originalLanguage.orEmpty(),
                        it.originalTitle.orEmpty(),
                        it.overview.orEmpty(),
                        it.popularity ?: 0.0,
                        it.posterPath,
                        it.releaseDate.orEmpty(),
                        it.title.orEmpty(),
                        it.video ?: false,
                        it.voteAverage ?: 0.0,
                        it.voteCount ?: 0,
                       apiPageIndex = null
                    )
                } ?: emptyList(),
                credits.casts.map {
                    MovieDetails.Cast(
                        it.adult,
                        it.gender,
                        it.id,
                        it.knownForDepartment,
                        it.name,
                        it.originalName,
                        it.popularity,
                        it.profilePath,
                        it.castId,
                        it.character,
                        it.creditId,
                        it.order
                    )
                }
            )
        }
        return entity
    }

    private fun Genre.asDbModel() = with(this) {
        MovieDetails.Genre(id ?: return@with null, name ?: return@with null)
    }
}


