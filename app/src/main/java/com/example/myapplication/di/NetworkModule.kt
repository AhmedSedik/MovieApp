package com.example.myapplication.di

import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.data.remote.RetrofitServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideMovieService(): MovieService = RetrofitServiceBuilder.retrofitService
}