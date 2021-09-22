package com.example.myapplication.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.myapplication.MainApplication
import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.data.remote.MovieServiceHelper
import com.example.myapplication.data.remote.MovieServiceHelperImpl
import com.example.myapplication.data.remote.RequestInterceptor
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
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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


    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit)
    = retrofit.create(MovieService::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
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
    fun providesMoshi():Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    @Singleton
    @Provides
    fun proviesMoshiConverterFactory(moshi: Moshi): Converter.Factory =
        MoshiConverterFactory.create(moshi)


    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: MovieServiceHelperImpl): MovieServiceHelper = apiHelper

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO







}