package com.example.myapplication.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.remote.MovieDto
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.extension.appendList
import com.example.myapplication.extension.liveDataBlockScope
import com.example.myapplication.util.MovieListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository)
    : ViewModel() ,GoToMovie{



    private val loadedPopularMovieList: LiveData<List<MovieDto>>
    private val popularPage = MutableLiveData<Int>().apply { value = 1 }

    val popularMovieList = MediatorLiveData<MutableList<MovieDto>>()


    private val _viewAllEvent = MutableLiveData<Event<MovieListType>>()


    val viewAllEvent: LiveData<Event<MovieListType>>
    get() = _viewAllEvent


    init {
        Timber.d("view Model initiated")

        loadedPopularMovieList = popularPage.switchMap {
            liveDataBlockScope {
                movieRepository.loadPopularList(it){}
            }


        }

        popularMovieList.addSource(loadedPopularMovieList){
            it?.let { list ->
                popularMovieList.appendList(list)
            }
        }

    }

    fun viewAll(movieListType: MovieListType) {
        Timber.d("ViewAll Clicked")
        _viewAllEvent.value = Event(movieListType)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("ViewModel Cleared")
    }

    fun loadMorePopular() {
        popularPage.value = popularPage.value?.plus(1)
    }

    override val goToMovieDetailsEvent: MutableLiveData<Event<MovieDto>>
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