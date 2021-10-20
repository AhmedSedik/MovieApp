package com.example.myapplication.data.local

import android.content.Context
import androidx.room.*
import com.example.movieapplication.models.pos.PopularPos
import com.example.movieapplication.models.pos.TopRatedPos
import com.example.movieapplication.models.pos.UpcomingPos
import com.example.myapplication.data.local.converters.Converter
import com.example.myapplication.data.model.entity.*
import com.example.myapplication.util.Constants
import com.squareup.moshi.Moshi

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */
@Database(
    entities = [
        Movie::class,
        MovieDetails::class,
        PopularKey::class,
        TopRatedKey::class,
        UpcomingKey::class,
        PopularPos::class,
        TopRatedPos::class,
        UpcomingPos::class], version = 1
)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao
    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun remoteKeyPopularDao(): RemoteKeyPopularDao
    abstract fun remoteKeyTopRatedDao(): RemoteKeyTopRatedDao
    abstract fun remoteKeyTUpcomingDao(): RemoteKeyUpcomingDao

    abstract fun popularPosDao(): PopularPosDao
    abstract fun topRatedPosDao(): TopRatedPosDao
    abstract fun upcomingPosDao(): UpcomingPosDao

    companion object {
        lateinit var moshi: Moshi
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java, Constants.DATABASE_NAME
            ).build()
    }
}
