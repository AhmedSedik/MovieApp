package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.example.myapplication.data.datasource.LocalMoviesDataSource
import com.example.myapplication.data.datasource.RemoteMovieDataSource
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.mapToDomain
import com.example.myapplication.data.mapToEntity
import com.example.myapplication.data.mediator.PageKeyedRemoteMediator
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.remote.movies.MovieDto
import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.extension.bodyOrThrow
import com.example.myapplication.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.net.UnknownHostException

import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

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


    suspend fun getAll(page: Int): ApiResult<List<MovieDomain>> = try {
        ApiResult.Success(
            movieService.getMoviesList(page).bodyOrThrow().results.orEmpty().map {
                it.mapToDomain()
            }
        )
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        ApiResult.Error(e)
    }

    suspend fun loadPopularList(page: Int, errorText: (String) -> Unit) =
        loadPageListCall(
            {
                movieService.getPopularMoviesCall(page)

            },
            MutableLiveData<List<MovieDto>>(),
            errorText
        )

    fun getMovies(): LiveData<List<Movie>> {
        return movieListDao.getMovieListLiveData()
    }


    suspend fun fetchPopularFromNetwork(page: Int) = liveData<List<MovieDto>> {
        try {
            val result = movieService.getPopularMovies(page)
            val movieList = result.body()?.results

            if (result.isSuccessful) {
                Timber.d("movie List: $movieList")

                movieList?.let {
                    movieListDao.insert(it.map {
                        it.mapToEntity(DEFAULT_PAGE_INDEX)
                    })
                }
                LoadingStatus.success()
            } else {
                LoadingStatus.error(ErrorCode.NO_DATA)
            }
        } catch (e: Exception) {
            LoadingStatus.error(ErrorCode.UNKNOWN_ERROR, e.message)
        } catch (e: UnknownHostException) {
            LoadingStatus.error(ErrorCode.NETWORK_ERROR)
        }


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

        }
    )

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