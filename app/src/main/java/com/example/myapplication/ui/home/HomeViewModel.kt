package com.example.myapplication.ui.home

import androidx.lifecycle.*
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.moviesusecase.GetMoviesUseCase
import com.example.myapplication.data.remote.movies.MovieDto
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.MovieListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.conflate
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val getMoviesUseCase: GetMoviesUseCase)
    : ViewModel() ,GoToMovie{


    //private val _loadingStatus = MutableLiveData<LoadingStatus>()
   // private val loadedPopularMovieList: LiveData<List<MovieDto>>
    private val popularPage = MutableLiveData<Int>().apply { value = 1 }

   // val popularMovieList = MediatorLiveData<MutableList<MovieDto>>()


    private val _viewAllEvent = MutableLiveData<Event<MovieListType>>()


    val viewAllEvent: LiveData<Event<MovieListType>>
    get() = _viewAllEvent


    val movies=  getMoviesUseCase.execute(Unit).conflate(). asLiveData()

    init {
        Timber.d("view Model initiated")

       /* loadedPopularMovieList = popularPage.switchMap { page->
            liveData(context = viewModelScope.coroutineContext + IO){
                emitSource(movieRepository.fetchPopularFromNetwork(page))
            }

        }*/
       /* loadedPopularMovieList = popularPage.switchMap { page->
            liveDataBlockScope {
                movieRepository.loadPopularList(page) { }
            }

        }

        popularMovieList.addSource(loadedPopularMovieList){
            it?.let { list ->
                popularMovieList.appendList(list)
            }
        }*/

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