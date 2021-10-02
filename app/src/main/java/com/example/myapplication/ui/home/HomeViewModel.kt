package com.example.myapplication.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.extension.appendList
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
private val DEFAULT_QUERY_TYPE = MovieListType.POPULAR

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,movieListType: MovieListType
) : ViewModel() {

    private val mutableSearchQueryFlow: MutableStateFlow<MovieListType>
        get() {
            TODO()
        }
    private val queryType = MutableLiveData(DEFAULT_QUERY_TYPE)


   /* val popularMoviesFlow: Flow<PagingData<MovieDomain>> =
        movieRepository.popularMoviesFlow.cachedIn(viewModelScope)*/



    val popularMovieList = MediatorLiveData<MutableList<Movie>>()

    fun getMovies(movieListType: MovieListType): Flow<PagingData<MovieDomain>> {
        return movieRepository.popularMoviesFlow(movieListType).cachedIn(viewModelScope)


    }


    fun navigateCollection(navigate: (query: MovieListType) -> Unit) {
        navigate.invoke(requireNotNull(queryType.value))
    }
    private val _viewAllEvent = MutableLiveData<Event<MovieListType>>()


    val viewAllEvent: LiveData<Event<MovieListType>>
    get() = _viewAllEvent


    init {
        Timber.d("view Model initiated")

    }

    fun viewAll(movieListType: MovieListType) {
        Timber.d("ViewAll Clicked")
        _viewAllEvent.value = Event(movieListType)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("ViewModel Cleared")
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