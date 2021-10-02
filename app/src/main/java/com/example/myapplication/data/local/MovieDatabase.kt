package com.example.myapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapplication.models.pos.PopularPos
import com.example.movieapplication.models.pos.TopRatedPos
import com.example.movieapplication.models.pos.UpcomingPos
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.PopularKey
import com.example.myapplication.data.model.entity.TopRatedKey
import com.example.myapplication.data.model.entity.UpcomingKey
import com.example.myapplication.util.Constants

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */
@Database(
    entities = [
        Movie::class,
        PopularKey::class,
        TopRatedKey::class,
        UpcomingKey::class,
        PopularPos::class,
        TopRatedPos::class,
        UpcomingPos::class], version = 1
)
abstract class MovieDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyPopularDao(): RemoteKeyPopularDao
    abstract fun remoteKeyTopRatedDao(): RemoteKeyTopRatedDao
    abstract fun remoteKeyTUpcomingDao(): RemoteKeyUpcomingDao

    abstract fun popularPosDao(): PopularPosDao
    abstract fun topRatedPosDao(): TopRatedPosDao
    abstract fun upcomingPosDao(): UpcomingPosDao

    companion object {

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
