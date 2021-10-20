package com.example.myapplication.data.model.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "movies")

data class Movie(

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val id: Long,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "backdrop_path")
    val backDropPath: String?,

    @ColumnInfo(name = "genre_ids")

    val genreIds: List<Int?>,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,

    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Double?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "video")
    val video: Boolean,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,

    @ColumnInfo(name = "api_page_index")
    val apiPageIndex: Int?


) : Parcelable
