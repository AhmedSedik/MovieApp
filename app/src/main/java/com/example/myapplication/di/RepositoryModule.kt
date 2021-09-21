package com.example.myapplication.di

import com.example.myapplication.data.remote.MovieServiceHelper
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.MovieListType
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/20/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(movieServiceHelper: MovieServiceHelper):
            MovieRepository = MovieRepository(movieServiceHelper)


    //TODO correct Provides later
    @Provides
     fun provideEnum()= MovieListType.POPULAR




}