package com.example.myapplication.data.local.converters

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json



/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */

open class Converter {

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

    /*@TypeConverter
    @ToJson
    fun toList(value: List<Int>?): String? = listOfIntAdapter.toJson(value)*/

   /* @TypeConverter
    fun fromString(value: String): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson<List<Int>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        val gson = Gson()
        return gson.toJson(list)
    }*/



}