package com.example.myapplication.data

import com.example.myapplication.data.model.cast.Cast
import com.example.myapplication.data.model.domain.CreditsDomain
import com.example.myapplication.data.model.domain.MovieDetailsDomain


import com.example.myapplication.data.model.domain.MovieDomain


import com.example.myapplication.data.remote.movies.MovieDto
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.data.remote.Credits
import com.example.myapplication.data.remote.movies.MovieDetailsDto

/**
 * Created by Ahmad Sedeek on 9/25/2021.
 */


fun Movie.mapToDomain(): MovieDomain = MovieDomain(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage,
    adult = adult,
    backdropPath = backdropPath,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    video = video,
    voteCount = voteCount,

)

fun MovieDto.mapToEntity(apiPageIndex: Int?): Movie = Movie(
    adult = adult,
    backdropPath = backdropPath,
    id = id,
    originalLanguage= originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount,

    apiPageIndex = apiPageIndex
)

fun mapMovieDtoToEntity(movieDto: MovieDto) = with(movieDto) {
    Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        adult = adult,
        popularity = popularity,
        originalLanguage = originalLanguage,
        originalTitle = originalLanguage,
        backdropPath = backdropPath,
        video = video,
        voteCount = voteCount,
        apiPageIndex = null
    )
}

fun MovieDto.toEntity() = mapMovieDtoToEntity(this)
fun List<MovieDto>.toEntity() = map {
    it.toEntity()
}

fun MovieDto.mapToDomain(): MovieDomain = MovieDomain(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage,
    adult = adult,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    video = video,
    voteCount = voteCount,
    backdropPath = backdropPath

)

fun mapMovieDtoToDomain(movieDto: Movie) = with(movieDto) {
    MovieDomain(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage,

        adult = adult,
        originalTitle = originalTitle,
        popularity = popularity,
        originalLanguage = originalLanguage,

        voteCount = voteCount,
        video = video,
        backdropPath = backdropPath
    )

}

fun Movie.toDomain() = mapMovieDtoToDomain(this)
fun List<Movie>.toDomain() = map {
    it.toDomain()
}

fun MovieDetails.toMovieDetails() = MovieDetailsDomain(
    genres.map {
        MovieDetailsDomain.Genre(it.id, it.name)
    },
    id,
    overview,
    posterPath,
    releaseDate,
    title,

    voteAverage,
    voteCount,
    popularity,
    status,
    recommendations?.map {
        MovieDomain(
            it.adult,
            it.backdropPath,
            it.id,
            it.originalLanguage,
            it.originalTitle,
            it.overview,
            it.popularity,
            it.posterPath,
            it.releaseDate,
            it.title,
            it.video,
            it.voteAverage,
            it.voteCount,
        )
    },
    cast?.map {
        CreditsDomain.Cast(
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
    },
)



fun MovieDetailsDto.toDetailsEntity() = {

}

/*fun MovieDetails.toDomainDetails() = mapDetailsToDomain(this)
fun List<MovieDetails>.toDomainDetails() = map {
    it.toDomainDetails()
}*/

/*
private fun MovieDetailsDto.toMovieEntity() = mapDetailsToMovieEntity(this)
fun List<MovieDetailsDto>.toMovieEntity() = map {
    it.toMovieEntity()
}*/

