package com.example.myapplication.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.example.myapplication.data.datasource.LocalMoviesDataSource
import com.example.myapplication.data.datasource.RemoteMovieDataSource
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.mapToDomain
import com.example.myapplication.data.mediator.PageKeyedRemoteMediator

import com.example.myapplication.data.model.domain.MovieDomain

import com.example.myapplication.data.remote.MovieService

import com.example.myapplication.util.*
import kotlinx.coroutines.flow.*

import java.util.concurrent.TimeUnit

import javax.inject.Inject


/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */

class MovieRepository @Inject constructor(
    private val moviesDatabase: MovieDatabase,
    private val movieService: MovieService,
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMoviesDataSource,
) : BaseRepository() {

    private val movieListDao = moviesDatabase.movieDao()


    fun popularMoviesFlow(movieListType: MovieListType): Flow<PagingData<MovieDomain>> =
        Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false,
            ),
            initialKey = null,
            remoteMediator = PageKeyedRemoteMediator(
                moviesDatabase,
                movieService,
                movieListType),
            pagingSourceFactory = {
                when (movieListType){
                    MovieListType.POPULAR-> movieListDao.getMoviesPaging()
                    MovieListType.UPCOMING -> TODO()
                    MovieListType.TOPRATED -> TODO()
                    MovieListType.IN_THEATERS -> TODO()
                    MovieListType.SEARCH -> TODO()
                }
            },
        ) .flow.map {
            it.map { entity ->
                entity.mapToDomain()
            }
        }
    fun getMovieDetails(id: Long) = networkBoundResource(
        query = {

            localMovieDataSource.getMovieById(movieId = id)
        },
        fetch = {
            remoteMovieDataSource.getMovieById(movieId = id)
        },
        saveFetchResult = { movieDetails->
            moviesDatabase.withTransaction {
                localMovieDataSource.deleteAll()
                localMovieDataSource.deleteDetails()
                localMovieDataSource.insertDetails(movieDetails)
            }
        },
        shouldFetch = { data->
            true
        }
    )

    private fun isMovieStale(lastUpdated: Long): Boolean {
        val oneDay = TimeUnit.DAYS.toMillis(1)
        return (System.currentTimeMillis() - oneDay) > lastUpdated
    }


    fun getPopularMovies(page: Int) = networkBoundResource(
        query = {
            localMovieDataSource.getMovies(page)
        },
        fetch = {
            remoteMovieDataSource.getMovies(page)

        },
        saveFetchResult = { movies ->
            moviesDatabase.withTransaction {
                localMovieDataSource.deleteAll()
                localMovieDataSource.insert(movies)

            }

        },shouldFetch = {
            true
        }
    )


    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }

}