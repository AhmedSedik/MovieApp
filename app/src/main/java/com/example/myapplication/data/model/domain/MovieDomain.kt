package com.example.myapplication.data.model.domain



data class MovieDomain(
    val adult: Boolean?,
    val backdropPath: String?,
    val id: Long,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,

    ) {
    val imageUrl: String?
        get() = posterPath?.let { "https://image.tmdb.org/t/p/w500$posterPath" }
}
