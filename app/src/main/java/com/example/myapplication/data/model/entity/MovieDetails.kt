package com.example.myapplication.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.myapplication.data.model.SpokenLanguage
import com.example.myapplication.data.model.graphics.Graphics
import com.example.myapplication.data.model.video.VideoResponse
import com.example.myapplication.data.remote.Credits
import com.example.myapplication.data.remote.Genre
import com.example.myapplication.data.remote.movies.MovieDetailsDto

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */

@Entity(tableName = "movie_details",
foreignKeys = [
  ForeignKey(
   entity = Movie::class,
  parentColumns =["movie_id"],
  childColumns = ["moviedetails_id"],
   onDelete = ForeignKey.CASCADE
  )
])
data class MovieDetails(

 @ColumnInfo(name = "adult")
 val adult: Boolean,

 @ColumnInfo(name = "backdrop_path")
 val backdropPath: String?,

/* @ColumnInfo(name = "belongs_to_collection")
 val belongsToCollection: MovieDetailsDto.Collection?,*/

 @ColumnInfo(name = "budget")
 val budget: Int?,

 @ColumnInfo(name = "genres")
 val genres: List<Genre>,

 @ColumnInfo(name = "homepage")
 val homepage: String?,

 @PrimaryKey
 @ColumnInfo(name = "moviedetails_id")
 val id: Long ,

 @ColumnInfo(name = "imdb_id")
 val imdbId: String?,

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

 /*@Json(name = "production_companies")
 val productionCompanies: List<ProductionCompany>,

 @Json(name = "production_countries")
 val productionCountries: List<ProductionCountry>,*/

 @ColumnInfo(name = "release_date")
 val releaseDate: String?,

 @ColumnInfo(name = "revenue")
 val revenue: Long?,
 @ColumnInfo(name = "runtime")
 val runtime: Int?,

 @ColumnInfo(name = "spoken_languages")
 val spokenLanguages: List<SpokenLanguage>,

 @ColumnInfo(name = "status")
 val status: String?,

 @ColumnInfo(name = "tagline")
 val tagline: String?,

 @ColumnInfo(name = "title")
 val title: String?,

 @ColumnInfo(name = "video")
 val video: Boolean,

 @ColumnInfo(name = "vote_average")
 val voteAverage: Double?,

 @ColumnInfo(name = "vote_count")
 val voteCount: Int?,

 // AppendToResponse
 @ColumnInfo(name = "images") val images: Graphics,
 @ColumnInfo(name = "credits") val credits: Credits,
 @ColumnInfo(name = "videos") val videoResponse: VideoResponse
) /*{
 data class Collection(
  @ColumnInfo(name = "id") val id: Int?,
  @ColumnInfo(name = "name") val name: String?,
  @ColumnInfo(name = "poster_path") val posterPath: String?,
  @ColumnInfo(name = "backdrop_path") val backdropPath: String?
 )
}*/