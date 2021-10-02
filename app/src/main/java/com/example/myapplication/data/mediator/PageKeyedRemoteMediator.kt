package com.example.myapplication.data.mediator

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.movieapplication.models.pos.PopularPos
import com.example.movieapplication.models.pos.TopRatedPos
import com.example.movieapplication.models.pos.UpcomingPos
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.PopularKey
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException
import com.example.myapplication.data.mapToEntity
import com.example.myapplication.data.model.entity.TopRatedKey
import com.example.myapplication.data.model.entity.UpcomingKey
import com.example.myapplication.data.model.response.MovieResponse
import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.util.MovieListType
import retrofit2.HttpException
import retrofit2.Response


/**
 * Created by Ahmad Sedeek on 9/25/2021.
 */
@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val database: MovieDatabase,
    private val movieService: MovieService,
    private val movieListType: MovieListType
) : RemoteMediator<Int, Movie>() {

    private val movieDao = database.movieDao()
    private val popularKeyDao = database.remoteKeyPopularDao()
    private val topRatedKeyDao = database.remoteKeyTopRatedDao()
    private val upcomingKeyDao = database.remoteKeyTUpcomingDao()

    private val popularPosDao = database.popularPosDao()
    private val topRatedPosDao = database.topRatedPosDao()
    private val upcomingPosDao = database.upcomingPosDao()

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {

        try {
            val loadKey = when (loadType) {
                LoadType.REFRESH ->{
                    Timber.d("MovieMediator REFRESH")
                    null
                }

                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKey(movieListType)
                    if (remoteKey == null) {
                        Timber.d("MovieMediatorAPPEND > Remote key: null")
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    remoteKey.plus(1)
                }
            }
            Timber.d("Loading with load key: $loadKey, load type: $loadType")

            val response = request(movieListType,loadKey)
            val movies = response.body()?.results.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    clearDatabase(movieListType)
                    movieDao.deleteAllPopularMovies()
                }
                if (response.isSuccessful) {

                    val size = response.body()!!.results.size
                    val page = response.body()!!.nextPage
                    val list = response.body()!!.results

                    when (movieListType) {
                        MovieListType.POPULAR -> {
                            list.forEachIndexed { index, movieDto ->
                                popularPosDao.insert(
                                    PopularPos(
                                        0,
                                        movieDto.id,
                                        getPosition(size, page, index.plus(1))

                                    )
                                )
                            }
                            popularKeyDao.insertOrReplace(
                                PopularKey(0,
                                        list.last().id,
                                    page
                                )
                            )
                        }
                        MovieListType.TOPRATED ->{
                            list.forEachIndexed{ index, movieDto ->
                                topRatedPosDao.insert(
                                    TopRatedPos(
                                        0,
                                        movieDto.id,
                                        getPosition(size,page,index.plus(1))
                                    )
                                )
                            }
                            topRatedKeyDao.insertOrReplace(
                                TopRatedKey(0,
                                    list.last().id,
                                    page)
                            )

                        }

                        MovieListType.UPCOMING -> {
                            list.forEachIndexed { index, movie ->
                                upcomingPosDao.insert(
                                    UpcomingPos(0, movie.id, getPosition(size,
                                        page, index.plus(1)))
                                )
                            }
                            upcomingKeyDao.insertOrReplace(
                                UpcomingKey(0, list.last().id, page)
                            )
                        }
                    }
                    movieDao.insert(movies.map {
                        it.mapToEntity(apiPageIndex = response.body()?.page) }
                        .also {
                            Timber.d("Loaded movies: $it")
                        })

                }else{
                    Timber.d("Mediator Request Failed")
                }



            }
            return MediatorResult.Success(
                endOfPaginationReached = movies.isEmpty()
                        || response.body()?.totalPages == response.body()?.page
            )
        } catch (e: CancellationException) {
            Timber.e(e)
            throw e
        } catch (e: Exception) {
            Timber.e(e)
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }
    private suspend fun request(movieListType: MovieListType, nextPage: Int?): Response<MovieResponse> {
        return when (movieListType) {

            MovieListType.POPULAR -> movieService.getPopularMovies(nextPage ?: 1)
            MovieListType.TOPRATED -> movieService.getTopRatedMovie(nextPage ?: 1)
            MovieListType.UPCOMING -> movieService.getUpcomingMovies(nextPage ?: 1)
            MovieListType.IN_THEATERS -> TODO()
            MovieListType.SEARCH -> TODO()
        }
    }
    private suspend fun getRemoteKey(movieListType: MovieListType): Int? {
        return when (movieListType) {

            MovieListType.POPULAR -> {
                popularKeyDao.lastItemRemoteKey()?.page
            }
            MovieListType.TOPRATED -> {
                topRatedKeyDao.lastItemRemoteKey()?.page
            }
            MovieListType.UPCOMING -> {
                upcomingKeyDao.lastItemRemoteKey()?.page

            }else-> upcomingKeyDao.lastItemRemoteKey()?.page
        }
    }
    private suspend fun clearDatabase(movieListType: MovieListType) {
        when (movieListType) {
            MovieListType.POPULAR -> {
                popularPosDao.deleteAll()
                popularKeyDao.deleteAll()

            }
            MovieListType.TOPRATED -> {
                topRatedPosDao.deleteAll()
                topRatedKeyDao.deleteAll()
            }
            MovieListType.UPCOMING -> {
                upcomingPosDao.deleteAll()
                upcomingKeyDao.deleteAll()
            }
        }
//        movieDao.deleteAll()
    }
    private fun getPosition(itemSize: Int, page: Int, currentPosition: Int): Int {
        return ((itemSize * page) - itemSize) + currentPosition
    }


    companion object {
        private const val POPULAR_MOVIES_REMOTE_KEY = "POPULAR_MOVIES_REMOTE_KEY"
        private const val TOPRATED_MOVIES_REMOTE_KEY = "TOPRATED_MOVIES_REMOTE_KEY"
        private const val UPCOMING_MOVIES_REMOTE_KEY = "UPCOMING_MOVIES_REMOTE_KEY"


    }

}