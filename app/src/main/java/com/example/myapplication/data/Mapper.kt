package com.example.myapplication.data

import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.remote.MovieDto
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
    voteAverage = voteAverage
)

fun MovieDto.mapToEntity(apiPageIndex: Int?): Movie = Movie(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    apiPageIndex = apiPageIndex
)

fun MovieDto.mapToDomain(): MovieDomain = MovieDomain(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview.orEmpty(),
    releaseDate = releaseDate.orEmpty(),
    voteAverage = voteAverage

)