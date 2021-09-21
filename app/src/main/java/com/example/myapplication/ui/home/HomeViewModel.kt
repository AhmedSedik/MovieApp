package com.example.myapplication.ui.home

import androidx.lifecycle.*
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.extension.liveDataBlockScope
import com.example.myapplication.util.MovieListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {



    private val page = MutableLiveData<Int>().apply { value = 1 }
    private val _popularMoviesLiveData: LiveData<List<Movie>>
    val popularMoviesMediatorLiveData = MediatorLiveData<List<Movie>>()

    private val _viewAllEvent = MutableLiveData<Event<MovieListType>>()

    val viewAllEvent: LiveData<Event<MovieListType>>
    get() = _viewAllEvent


    init {
        Timber.d("view Model initiated")

        _popularMoviesLiveData = page.switchMap { page->
            liveData(context = viewModelScope.coroutineContext +Dispatchers.IO) {
                emitSource(movieRepository.getPopularMovies(page){})
            }
        }

        popularMoviesMediatorLiveData.addSource(_popularMoviesLiveData) {
            popularMoviesMediatorLiveData.value = it
        }


    }


    fun loadMorePopularMovies() {
        page.value = page.value?.plus(1)
    }

    fun viewAll(movieListType: MovieListType) {
        Timber.d("ViewAll Clicked")
        _viewAllEvent.value = Event(movieListType)
    }

}

/*_popularMoviesLiveData = page.switchMap {
          liveDataBlockScope {
              movieRepository.getPopularMovies(it) {}
          }
      }*/

/*  popularMoviesLiveData.addSource(loadPopularMovies) { movies ->
           movies?.let { list ->
               popularMoviesLiveData.appendList(list)
           }
       }*/