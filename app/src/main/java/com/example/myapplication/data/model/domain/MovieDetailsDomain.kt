package com.example.myapplication.data.model.domain


import com.example.myapplication.data.model.graphics.Graphics
import com.example.myapplication.data.model.video.VideoResponse
import com.example.myapplication.data.remote.Credits

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */


data class MovieDetailsDomain(

    val genres: List<Genre>,
    val id: Int,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String,
//    val modifiedAt: Long,
    val voteAverage: Double?,
    val voteCount: Int?,
    val popularity: Double,
    val status: String,
    val recommendations: List<MovieDomain>?,
    val cast: List<CreditsDomain.Cast>?,

    // AppendToResponse
    //val images: Graphics? = null,
    val credits: CreditsDomain? =null,
    //val videoResponse: VideoResponse? = null
){

    data class Genre(
        val id: Int,
        val name: String,
    )
}