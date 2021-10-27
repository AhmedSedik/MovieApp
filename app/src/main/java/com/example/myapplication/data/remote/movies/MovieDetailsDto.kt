package com.example.myapplication.data.remote.movies

import com.example.myapplication.data.model.SpokenLanguage
import com.example.myapplication.data.model.cast.Cast
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.graphics.Graphics
import com.example.myapplication.data.model.response.MovieResponse
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

 @field:Json(name = "id") val id: Int?,
 @field:Json(name = "adult") val adult: Boolean?,
 @field:Json(name = "backdrop_path") val backdropPath: String?,
 @field:Json(name = "budget") val budget: Int?,
 @field:Json(name = "genres") val genres: List<Genre>?,
 @field:Json(name = "homepage") val homepage: String?,
 @field:Json(name = "imdb_id") val imdbId: String?,
 @field:Json(name = "original_language") val originalLanguage: String?,
 @field:Json(name = "original_title") val originalTitle: String?,
 @field:Json(name = "overview") val overview: String?,
 @field:Json(name = "popularity") val popularity: Double?,
 @field:Json(name = "poster_path") val posterPath: String?,
 @field:Json(name = "production_companies") val productionCompanies: List<ProductionCompany>?,
 @field:Json(name = "production_countries") val productionCountries: List<ProductionCountry>?,
 @field:Json(name = "release_date") val releaseDate: String?,
 @field:Json(name = "revenue") val revenue: Int?,
 @field:Json(name = "runtime") val runtime: Int?,
 @field:Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguage>?,
 @field:Json(name = "status") val status: String?,
 @field:Json(name = "tagline") val tagline: String?,
 @field:Json(name = "title") val title: String?,
 @field:Json(name = "video") val video: Boolean?,
 @field:Json(name = "vote_average") val voteAverage: Double?,
 @field:Json(name = "vote_count") val voteCount: Int?,
// @field:Json(name = "watch/providers") val watchProviders: MovieWatchProviders?,
 @field:Json(name = "recommendations") val recommendations: MovieResponse,
 @field:Json(name = "cast") val cast: Cast,

 @field:Json(name = "credits") val credits: Credits,

// @Json(name = "images") val images: Graphics,
// @Json(name = "videos") val videoResponse: VideoResponse
){

 data class ProductionCompany(
  @field:Json(name = "id") val id: Int?,
  @field:Json(name = "logo_path") val logoPath: String?,
  @field:Json(name = "name") val name: String?,
  @field:Json(name = "origin_country") val originCountry: String?
 )

 data class ProductionCountry(
  @field:Json(name = "iso_3166_1") val iso31661: String?,
  @field:Json(name = "name") val name: String?
 )
}