package com.example.myapplication.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.model.response.BaseListResponse
import com.example.myapplication.data.model.response.MovieResponse
import com.example.myapplication.data.remote.MovieService
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.MovieListType
import retrofit2.Call
import java.lang.Exception

/*class MovieDataSource(
    private val movieService: MovieService,
    private val movieRepository: MovieRepository,
    private val movieListType: MovieListType
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val response = movieService.getMoviesList(page = nextPage)

            LoadResult.Page(
                data = response.results
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    @ExperimentalPagingApi
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }


}*/
