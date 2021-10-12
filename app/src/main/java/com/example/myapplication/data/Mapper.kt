package com.example.myapplication.data

import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.remote.movies.MovieDto
import com.example.myapplication.data.model.entity.Movie

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

fun mapMovieDtoToEntity(movieDto: MovieDto) = with(movieDto){
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

fun mapMovieDtoToDomain(movieDto: Movie) = with(movieDto){
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