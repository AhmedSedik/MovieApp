package com.example.myapplication.di

import com.example.myapplication.data.datasource.LocalMoviesDataSource
import com.example.myapplication.data.datasource.RemoteMovieDataSource
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.MovieListType
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/20/2021.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMovieRepository(
        moviesDatabase: MovieDatabase,
        movieServiceHelper: MovieService,
        remoteMovieDataSource: RemoteMovieDataSource,
        localMovieDataSource: LocalMoviesDataSource
    ): MovieRepository = MovieRepository(
        moviesDatabase, movieServiceHelper,
        remoteMovieDataSource, localMovieDataSource
    )




    //TODO correct Provides later
    @Provides
    fun provideEnum() = MovieListType.POPULAR



}