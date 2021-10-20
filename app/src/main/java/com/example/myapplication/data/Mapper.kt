package com.example.myapplication.data

import com.example.myapplication.data.model.cast.Cast
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
    backDropPath = backDropPath,
    genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    video = video,
    voteCount = voteCount
)

fun MovieDto.mapToEntity(apiPageIndex: Int?): Movie = Movie(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    genreIds = genreIds,
    adult = adult,
    popularity = popularity,
    originalLanguage = originalLanguage,
    originalTitle = originalLanguage,
    backDropPath = backDropPath,
    video = video,
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
        genreIds = genreIds,
        adult = adult,
        popularity = popularity,
        originalLanguage = originalLanguage,
        originalTitle = originalLanguage,
        backDropPath = backDropPath,
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
    backDropPath = backDropPath,
    genreIds = genreIds,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    video = video,
    voteCount = voteCount

)

fun mapMovieDtoToDomain(movieDto: Movie) = with(movieDto) {
    MovieDomain(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview.orEmpty(),
        releaseDate = releaseDate.orEmpty(),
        voteAverage = voteAverage,
        backDropPath = backDropPath,
        adult = adult,
        originalTitle = originalTitle,
        popularity = popularity,
        originalLanguage = originalLanguage,
        genreIds = genreIds,
        voteCount = voteCount,
        video = video
    )

}

fun Movie.toDomain() = mapMovieDtoToDomain(this)
fun List<Movie>.toDomain() = map {
    it.toDomain()
}
fun MovieDetails.mapDetailsToDomain():MovieDetailsDomain =
    MovieDetailsDomain(
        id = id,
        title = title,
        posterPath = posterPath,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        backdropPath = backdropPath,
        adult = adult,
        originalTitle = originalTitle,
        popularity = popularity,
        originalLanguage = originalLanguage,
        voteCount = voteCount,
        video = video,
        budget = budget,
        credits = credits,
        genres = genres,
        homepage = homepage,
        images = images,
        imdbId = imdbId,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages,
        status = status,
        tagline = tagline,
        videoResponse = videoResponse

    )

/*fun MovieDetails.toDomainDetails() = mapDetailsToDomain(this)
fun List<MovieDetails>.toDomainDetails() = map {
    it.toDomainDetails()
}*/
fun MovieDetailsDto.mapDetailsToMovieEntity():MovieDetails=
    MovieDetails(
        id = id,
        adult = adult,
        overview = overview,
        popularity = popularity,
        title = title,
        video = video,
        voteCount = voteCount,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        posterPath = posterPath,
        releaseDate = releaseDate,
        videoResponse = videoResponse,
        tagline = tagline,
        status = status,
        spokenLanguages = spokenLanguages,
        runtime = runtime,
        revenue = revenue,
        imdbId = imdbId,
        images = images,
        homepage = homepage,
        genres = genres,
        credits = credits,
        budget = budget,
        backdropPath = backdropPath
    )


/*
private fun MovieDetailsDto.toMovieEntity() = mapDetailsToMovieEntity(this)
fun List<MovieDetailsDto>.toMovieEntity() = map {
    it.toMovieEntity()
}*/

