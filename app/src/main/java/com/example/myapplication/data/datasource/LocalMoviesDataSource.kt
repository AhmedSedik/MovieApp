package com.example.myapplication.data.datasource

import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.repository.MovieCache
import com.example.myapplication.data.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 10/11/2021.
 */
class LocalMoviesDataSource @Inject constructor(
        private val movieDao: MovieDao
) : MovieCache{
        override fun getMovies(page: Int?): Flow<List<MovieDomain>> = movieDao.getMoviesFlow().map {
            it.toDomain()
        }


        override suspend fun insert(movies: List<Movie>) {
             movieDao.insert(movies)
        }

        override suspend fun deleteAll() {
               movieDao.deleteAllPopularMovies()
        }
}