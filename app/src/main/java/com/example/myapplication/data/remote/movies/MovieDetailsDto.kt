package com.example.myapplication.data.remote.movies

import com.example.myapplication.data.model.SpokenLanguage
import com.example.myapplication.data.model.graphics.Graphics
import com.example.myapplication.data.model.video.VideoResponse
import com.example.myapplication.data.remote.Credits
import com.example.myapplication.data.remote.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
@JsonClass(generateAdapter = true)
data class MovieDetailsDto(

 @Json(name = "adult")
 val adult: Boolean,

 @Json(name = "backdrop_path")
 val backdropPath: String?,

 /*@Json(name = "belongs_to_collection")
 val belongsToCollection: Collection?,*/

 @Json(name = "budget")
 val budget: Int,

 @Json(name = "genres")
 val genres: List<Genre>,

 @Json(name = "homepage")
 val homepage: String?,

 @Json(name = "id")
 val id: Long,

 @Json(name = "imdb_id")
 val imdbId: String?,

 @Json(name = "original_language")
 val originalLanguage: String?,

 @Json(name = "original_title")
 val originalTitle: String?,

 @Json(name = "overview")
 val overview: String?,

 @Json(name = "popularity")
 val popularity: Double?,

 @Json(name = "poster_path")
 val posterPath: String?,

 /*@Json(name = "production_companies")
 val productionCompanies: List<ProductionCompany>,

 @Json(name = "production_countries")
 val productionCountries: List<ProductionCountry>,*/

 @Json(name = "release_date")
 val releaseDate: String?,

 @Json(name = "revenue")
 val revenue: Long?,
 @Json(name = "runtime") val runtime: Int?,

 @Json(name = "spoken_languages")
 val spokenLanguages: List<SpokenLanguage>,

 @Json(name = "status")
 val status: String?,

 @Json(name = "tagline")
 val tagline: String?,

 @Json(name = "title")
 val title: String?,

 @Json(name = "video")
 val video: Boolean,

 @Json(name = "vote_average")
 val voteAverage: Double?,

 @Json(name = "vote_count")
 val voteCount: Int?,

 // AppendToResponse
 @Json(name = "images") val images: Graphics,
 @Json(name = "credits") val credits: Credits,
 @Json(name = "videos") val videoResponse: VideoResponse
)