package com.example.myapplication.data.model.domain

import androidx.room.PrimaryKey
import coil.map.Mapper
import com.example.myapplication.data.model.SpokenLanguage
import com.example.myapplication.data.model.graphics.Graphics
import com.example.myapplication.data.model.video.VideoResponse
import com.example.myapplication.data.remote.Credits
import com.example.myapplication.data.remote.Genre
import com.example.myapplication.data.remote.movies.MovieDetailsDto

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */


data class MovieDetailsDomain(

    val adult: Boolean,
    val backdropPath: String?,
//    val belongsToCollection: MovieDetailsDto.Collection?,
    val genres: List<Genre>,
    val budget: Int?,
    val homepage: String?,
    val id: Long,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,

    /*@Json(name = "production_companies")
    val productionCompanies: List<ProductionCompany>,

    @Json(name = "production_countries")
    val productionCountries: List<ProductionCountry>,*/

    val releaseDate: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean,
    val voteAverage: Double?,
    val voteCount: Int?,

    // AppendToResponse
    val images: Graphics,
    val credits: Credits,
    val videoResponse: VideoResponse
)/* {
 data class Collection(
  val id: Int?,
  val name: String?,
   val posterPath: String?,
  val backdropPath: String?
 )

 //Contract Overview to 150 characters max ending with a whole word followed by "..."
 val shortOverview: String
  get() = overview?.substring(0, 150)?.replaceAfterLast(" ", "")?.trim().plus("...")
}*/