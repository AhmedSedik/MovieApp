package com.example.myapplication.ui.home

import androidx.lifecycle.*
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.extension.appendList
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
) : ViewModel() ,GoToMovie{



    private val page = MutableLiveData<Int>().apply { value = 1 }
    private val _popularMovieList: LiveData<List<Movie>>
    val popularMovieList = MediatorLiveData<MutableList<Movie>>()

    private val _viewAllEvent = MutableLiveData<Event<MovieListType>>()


    val viewAllEvent: LiveData<Event<MovieListType>>
    get() = _viewAllEvent


    init {
        Timber.d("view Model initiated")

        _popularMovieList = page.switchMap { page->
            liveData(context = viewModelScope.coroutineContext +Dispatchers.IO) {
                emitSource(movieRepository.getPopularMovies(page){})
            }
        }

        popularMovieList.addSource(_popularMovieList) {
            it?.let {
                popularMovieList.appendList(it)
            }
        }
    }


    fun loadMorePopularMovies() {
        page.value = page.value?.plus(1)
    }

    fun viewAll(movieListType: MovieListType) {
        Timber.d("ViewAll Clicked")
        _viewAllEvent.value = Event(movieListType)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("ViewModel Cleared")
    }

    override val goToMovieDetailsEvent: MutableLiveData<Event<Movie>>
        get() = TODO("Not yet implemented")
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