package com.example.myapplication.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "movies")
@JsonClass(generateAdapter = true)
data class Movie(
    @PrimaryKey
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "backdrop_path")
    val backDropPath: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Double?,
    @Json(name = "vote_count")
    val voteCount: Int?,
    @Json(name = "release_date")
    val releaseDate: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "overview")
    val overview: String?


):Parcelable
