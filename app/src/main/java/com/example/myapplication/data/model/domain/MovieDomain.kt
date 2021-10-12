package com.example.myapplication.data.model.domain

import androidx.room.ColumnInfo

data class MovieDomain(
    val id: Int,
    val title: String?,
    val adult: Boolean,
    val backDropPath: String?,
    val genreIds: List<Int?> = listOf(),
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String?,
    val video: Boolean,
    val voteAverage: Double?,
    val voteCount: Int?,

    ) {
    val imageUrl: String?
        get() = posterPath?.let { "https://image.tmdb.org/t/p/w500$posterPath" }
}
