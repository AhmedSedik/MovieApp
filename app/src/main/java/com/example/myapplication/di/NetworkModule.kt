package com.example.myapplication.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myapplication.BuildConfig
import com.example.myapplication.MainApplication
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.local.converters.GenreCustomAdapter
import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.data.remote.RequestInterceptor
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.Constants.BASE_URL
import com.example.myapplication.util.MovieListType
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = BASE_URL


    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level =  if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okhHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .client(okhHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Singleton
    @Provides
    fun providesMoshi():Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory()).add(GenreCustomAdapter())
        .build()

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)


    /*@Provides
    @Singleton
    fun provideApiHelper(apiHelper: MovieServiceHelperImpl): MovieServiceHelper = apiHelper*/

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

/*    @Provides
    @Singleton
    fun provideMovieRepo(
        moviesDatabase: MovieDatabase,
        movieService: MovieServiceHelperImpl) = MovieRepository(moviesDatabase, movieService)*/








}