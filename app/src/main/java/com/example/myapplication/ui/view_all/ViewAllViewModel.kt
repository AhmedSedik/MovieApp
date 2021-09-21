package com.example.myapplication.ui.view_all

import androidx.lifecycle.*
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.MovieListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 9/20/2021.
 */

@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    movieListType: MovieListType
) : ViewModel() {

    private val page = MutableLiveData<Int>().apply { value = 1 }

    private val _moviesListLiveData: LiveData<List<Movie>>
    val moviesListMediatorLiveData = MediatorLiveData<List<Movie>>()


    init {
        Timber.d("ShowALlViewModel")
        _moviesListLiveData = when (movieListType) {
            MovieListType.POPULAR -> {
                page.switchMap { page ->
                    liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                        emitSource(movieRepository.getPopularMovies(page) {})
                    }
                }
            }
            else -> page.switchMap {
                liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {

                }
            }
        }

        moviesListMediatorLiveData.addSource(_moviesListLiveData) { list ->
            moviesListMediatorLiveData.value = list

        }


    }

}