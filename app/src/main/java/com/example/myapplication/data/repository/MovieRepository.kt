package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.mapToDomain
import com.example.myapplication.data.mediator.PageKeyedRemoteMediator
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.extension.bodyOrThrow
import com.example.myapplication.util.ApiResult
import com.example.myapplication.util.MovieListType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.cancellation.CancellationException

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */

class MovieRepository @Inject constructor(
    private val moviesDatabase: MovieDatabase,
    private val movieService: MovieService,
    movieListType: MovieListType
) {


    fun  popularMoviesFlow(movieListType: MovieListType): Flow<PagingData<MovieDomain>> =
        Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE),
            remoteMediator = PageKeyedRemoteMediator(moviesDatabase, movieService,movieListType)
        ) {
            moviesDatabase.movieDao().getMoviesFlow()
        }.flow.map {
            it.map { entity ->
                entity.mapToDomain()
            }
        }


    suspend fun getAll(page: Int): ApiResult<List<MovieDomain>> = try {
        ApiResult.Success(
            movieService.getMoviesList(page).bodyOrThrow().results.orEmpty().map {
                it.mapToDomain()
            }
        )
    }catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        ApiResult.Error(e)
    }

    /*suspend fun getMovieById(movieId: Int): MovieDomain? =
        moviesDatabase.movieDao().getMovieById(movieId = movieId)?.mapToDomain()*/
/*
    suspend fun searchForMovieTitle(title: String): ApiResult<List<MovieDomain>> = try {
        ApiResult.Success(
            movieService.searchForMovieTitle(title = title)
                .bodyOrThrow()
                .movies
                .orEmpty()
                .map { it.mapToDomain() }
        )
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        ApiResult.Error(e)
    }*/

    suspend fun deleteAllPopularMovies() {
        moviesDatabase.movieDao().deleteAllPopularMovies()
    }

    suspend fun changeNamesOfAllPopularMovies() {
        val movies = moviesDatabase.movieDao().getMovieList()
        moviesDatabase.movieDao().updateMovies(movies.map { it.copy(title = it.title + " (up)") })
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 20
    }

}