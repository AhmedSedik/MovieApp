package com.example.myapplication.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.myapplication.data.model.SpokenLanguage
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.data.model.graphics.Graphics
import com.example.myapplication.data.model.response.MovieResponse
import com.example.myapplication.data.model.video.VideoResponse
import com.example.myapplication.data.remote.Credits

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.lang.reflect.Type


/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
@ProvidedTypeConverter
 class Converter (private val moshi: Moshi){

        private val json = Json { ignoreUnknownKeys = true; prettyPrint = true }

   /* private val moshi = Moshi.Builder().build()
    private val listOfIntAdapter: JsonAdapter<List<Int>> =
        moshi.adapter<List<Int>>(Types.newParameterizedType(List::class.java, Integer::class.java)).nonNull()*/
    @TypeConverter
    fun serializing(genre: List<Int>): String {
        return json.encodeToString(ListSerializer(Int.serializer()), genre)
    }

    @TypeConverter
    fun parsing(str: String): List<Int> {
        return json.decodeFromString(ListSerializer(Int.serializer()), str)
    }

    @TypeConverter
    fun fromMoviesJson(
        value: String
    ): List<Movie>? {
        val type = Types.newParameterizedType(
            List::class.java,
            Movie::class.java)

        val adapter: JsonAdapter<List<Movie>> = moshi.adapter(type)
        return if (value.isEmpty()) null else adapter.fromJson(value)
    }

    @TypeConverter
    fun toMoviesJson(
        list: List<Movie>
    ): String {
        val type = Types.newParameterizedType(
            List::class.java,
            Movie::class.java)

        val adapter: JsonAdapter<List<Movie>> = moshi.adapter(type)
        return adapter.toJson(list)
    }

    //gg
    /*@TypeConverter
    fun fromMovieJson(
        value: String
    ): List<MovieResponse>? {
        val type = Types.newParameterizedType(
            List::class.java,
            MovieResponse::class.java)

        val adapter: JsonAdapter<List<MovieResponse>> = moshi.adapter(type)
        return if (value.isEmpty()) null else adapter.fromJson(value)
    }

    @TypeConverter
    fun toMovieJson(
        list: List<MovieResponse>
    ): String {
        val type = Types.newParameterizedType(
            List::class.java,
            MovieResponse::class.java)

        val adapter: JsonAdapter<List<MovieResponse>> = moshi.adapter(type)
        return adapter.toJson(list)
    }*/

    @TypeConverter
    fun fromMovieGenreJson(
        value: String
    ): List<MovieDetails.Genre>? {
        val type = Types.newParameterizedType(
            List::class.java,
            MovieDetails.Genre::class.java)

        val adapter: JsonAdapter<List<MovieDetails.Genre>> = moshi.adapter(type)
        return if (value.isEmpty()) null else adapter.fromJson(value)
    }

    @TypeConverter
    fun toMovieGenreJson(
        list: List<MovieDetails.Genre>
    ): String {
        val type = Types.newParameterizedType(
            List::class.java,
            MovieDetails.Genre::class.java)

        val adapter: JsonAdapter<List<MovieDetails.Genre>> = moshi.adapter(type)
        return adapter.toJson(list)
    }



    @TypeConverter
    fun creditToString(json: String?): Credits? {
        if (json == null) return null
        val type = Types.newParameterizedType( Credits::class.java)
        val adapter: JsonAdapter<Credits> = moshi.adapter(type)
        return adapter.fromJson(json)
    }

    @TypeConverter
    fun stringToCredit(credits: Credits): String {
        val type = object : TypeToken<String>(){}.type
        return Gson().toJson(credits,type)
    }
    //TODO: implement for all converters

    @TypeConverter
    fun spokenLanguageToString(value: String): List<SpokenLanguage> {
        val listType: Type = object : TypeToken<List<SpokenLanguage?>?>(){}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun stringToSpokenLanguage(spokenLanguage: List<SpokenLanguage>): String {
        val gson= Gson()
        return gson.toJson(spokenLanguage)
    }

    //Recommendations
    @TypeConverter
    fun recommendationsToString(value: String): List<MovieResponse> {
        val listType: Type = object : TypeToken<List<MovieResponse?>?>(){}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun stringToRecommendation(recommendations: List<MovieResponse>): String {
        val gson= Gson()
        return gson.toJson(recommendations)
    }

    @TypeConverter
    fun videoResponseToString(json: String): VideoResponse {
        val listType: Type = object : TypeToken<VideoResponse?>(){}.type
        return Gson().fromJson(json,listType)
    }

    @TypeConverter
    fun stringToVideoResponse(videoResponse: VideoResponse): String {
        val gson= Gson()
        return gson.toJson(videoResponse)
    }

    @TypeConverter
    fun graphicsToString(json: String): Graphics {
        val listType: Type = object : TypeToken<Graphics?>(){}.type
        return Gson().fromJson(json,listType)
    }

    @TypeConverter
    fun stringToGraphics(videoResponse: Graphics): String {
        val gson= Gson()
        return gson.toJson(videoResponse)
    }


   /* @TypeConverter
    fun collectionToString(json: String): MovieDetailsDto.Collection {
        val listType: Type = object : TypeToken<MovieDetails.Collection?>(){}.type
        return Gson().fromJson(json,listType)
    }

    @TypeConverter
    fun stringToCollection(videoResponse:MovieDetailsDto.Collection ): String {
        val gson= Gson()
        return gson.toJson(videoResponse)
    }*/

    @TypeConverter
    fun fromMovieCastJson(
        value: String
    ): List<MovieDetails.Cast>? {
        val type = Types.newParameterizedType(
            List::class.java,
            MovieDetails.Cast::class.java)

        val adapter: JsonAdapter<List<MovieDetails.Cast>> = moshi.adapter(type)
        return if (value.isEmpty()) null else adapter.fromJson(value)
    }

    @TypeConverter
    fun toMovieCastJson(
        list: List<MovieDetails.Cast>
    ): String {
        val type = Types.newParameterizedType(
            List::class.java,
            MovieDetails.Cast::class.java)

        val adapter: JsonAdapter<List<MovieDetails.Cast>> = moshi.adapter(type)
        return adapter.toJson(list)
    }


   /* @TypeConverter
    fun crewToString(value: String): List<Crew> {
        val listType: Type = object : TypeToken<List<Cast?>?>(){}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun stringToCrew(credits: List<Crew>): String {
        val type = object : TypeToken<List<Crew>>(){}.type
        return Gson().toJson(credits,type)
    }*/


}

