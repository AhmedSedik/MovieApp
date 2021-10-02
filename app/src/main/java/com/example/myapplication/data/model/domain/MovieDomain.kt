package com.example.myapplication.data.model.domain

import androidx.room.ColumnInfo

data class MovieDomain(
    val id: Int?,
    val title: String?,
    /*@Json(name = "genre_ids")
    val genreIds: List<Int?>*/
    //val backDropPath: String?,

    val posterPath: String?,

    val voteAverage: Double?,

    //val voteCount: Int?,

    val releaseDate: String?,

    //val originalLanguage: String?,

    val overview: String?,

    ) {
    val imageUrl: String?
        get() = posterPath?.let { "https://image.tmdb.org/t/p/w500$posterPath" }
}
