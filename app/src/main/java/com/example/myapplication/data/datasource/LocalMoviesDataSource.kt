package com.example.myapplication.data.datasource

import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieDetailsDao
import com.example.myapplication.data.mapToDomain
import com.example.myapplication.data.model.domain.MovieDetailsDomain
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.entity.MovieDetails
import com.example.myapplication.data.repository.MovieCache
import com.example.myapplication.data.toMovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
@Singleton
class LocalMoviesDataSource @Inject constructor(
        private val movieDao: MovieDao,
        private val movieDetailsDao: MovieDetailsDao
) : MovieCache{
        override fun getMovies(page: Int?): Flow<List<MovieDomain>> =
            movieDao.getMoviesFlow().map {
            it.map { movie->
                movie.mapToDomain()
            }
        }
//here is the problem of mapping
    //TODO:
    override fun getMovieById(movieId: Long): Flow<MovieDetailsDomain?> =
        movieDetailsDao.getMovieDetailsDistinctUntilChanged(movieId).map {
            it?.toMovieDetails()
        }

    override suspend fun insert(movies: List<Movie>) {
             movieDao.insert(movies)
        }

    override suspend fun insertDetails(movie: MovieDetails?) {
        movieDetailsDao.insertMovie(movie)
    }

    override suspend fun deleteAll() {
               movieDao.deleteAllPopularMovies()
        }

    override suspend fun deleteDetails() {
        movieDetailsDao.deleteAllMovieDetails()
    }
}