package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.local.MovieDetailsDao
import com.example.myapplication.data.local.converters.Converter
import com.example.myapplication.util.Constants
import com.example.myapplication.util.Constants.DATABASE_NAME
import com.example.myapplication.util.MovieListType
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule{


    @Provides
    @Singleton
    fun providesRoomDb(
        @ApplicationContext context: Context,
        @MoshiDefault moshi: Moshi
    ) = Room.databaseBuilder(context, MovieDatabase::class.java, Constants.DATABASE_NAME)
        .addTypeConverter(Converter(moshi))
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    fun provideMovieDao(movieDb: MovieDatabase): MovieDao {
        return movieDb.movieDao()
    }
    @Provides
    fun provideMovieDetailsDao(movieDb: MovieDatabase): MovieDetailsDao {
        return movieDb.movieDetailsDao()
    }
/*
    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context):MovieDatabase =
        MovieDatabase.getInstance(context)*/

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())




}