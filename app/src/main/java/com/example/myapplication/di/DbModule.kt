package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/22/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object DbModule{

    @Provides
    fun provideMovieDao(movieDb: MovieDatabase): MovieDao {
        return movieDb.movieDao()
    }

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context):MovieDatabase =
        MovieDatabase.getInstance(context)
}