package com.example.myapplication.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.remote.Credits

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */

@Entity(tableName = "movie_details")
data class MovieDetails(

 @PrimaryKey @ColumnInfo(name = "id") val id: Int,
 @ColumnInfo(name = "overview") val overview: String,
 @ColumnInfo(name = "poster_path") val posterPath: String?,
 @ColumnInfo(name = "release_date") val releaseDate: String?,
 @ColumnInfo(name = "title") val title: String,
 @ColumnInfo(name = "genres") val genres: List<Genre>,
// @ColumnInfo(name = "modified_at") val modifiedAt: Long,
 @ColumnInfo(name = "popularity") val popularity: Double,
 @ColumnInfo(name = "vote_average") val voteAverage: Double?,
 @ColumnInfo(name = "vote_count") val voteCount: Int?,
 @ColumnInfo(name = "status") val status: String,
 @ColumnInfo(name = "recommendations") val recommendations: List<Movie>?,
 @ColumnInfo(name = "cast") val cast: List<Cast>?,

 // AppendToResponse
 //@ColumnInfo(name = "images") val images: Graphics? = null,
 @ColumnInfo(name = "credits") val credits: Credits? = null,
 //@ColumnInfo(name = "videos") val videoResponse: VideoResponse? = null
){
 data class Genre(
  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "name") val name: String,
 )



 data class Cast(
  @ColumnInfo(name = "adult") val adult: Boolean,
  @ColumnInfo(name = "gender") val gender: Int?,
  @ColumnInfo(name = "id") val id: Int,
  @ColumnInfo(name = "known_for_department") val knownForDepartment: String,
  @ColumnInfo(name = "name") val name: String,
  @ColumnInfo(name = "original_name") val originalName: String,
  @ColumnInfo(name = "popularity") val popularity: Double,
  @ColumnInfo(name = "profile_path") val profilePath: String?,
  @ColumnInfo(name = "cast_id") val castId: Int,
  @ColumnInfo(name = "character") val character: String,
  @ColumnInfo(name = "credit_id") val creditId: String,
  @ColumnInfo(name = "order") val order: Int,
 )

/* data class Movies(
  @ColumnInfo(name = "adult") val adult: Boolean,
  @ColumnInfo(name = "backdrop_path") val backdropPath: String?,
  @ColumnInfo(name = "id") val id: Long,
  @ColumnInfo(name = "original_language") val originalLanguage: String,
  @ColumnInfo(name = "original_title") val originalTitle: String,
  @ColumnInfo(name = "overview") val overview: String,
  @ColumnInfo(name = "popularity") val popularity: Double,
  @ColumnInfo(name = "poster_path") val posterPath: String?,
  @ColumnInfo(name = "release_date") val releaseDate: String,
  @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "video") val video: Boolean,
  @ColumnInfo(name = "vote_average") val voteAverage: Double,
  @ColumnInfo(name = "vote_count") val voteCount: Int,
 )*/
}