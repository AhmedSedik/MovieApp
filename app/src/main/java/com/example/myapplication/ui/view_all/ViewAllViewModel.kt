package com.example.myapplication.ui.view_all

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.room.ExperimentalRoomApi
import com.example.myapplication.data.GoToMovie
import com.example.myapplication.data.mediator.PageKeyedRemoteMediator
import com.example.myapplication.data.model.Event
import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.remote.MovieDto
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.extension.appendList
import com.example.myapplication.util.MovieListType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 9/20/2021.
 */

@HiltViewModel
class ViewAllViewModel @ExperimentalCoroutinesApi @Inject constructor(
    private val movieRepository: MovieRepository, private val movieListType: MovieListType
) : ViewModel() {


    private val mutableSearchQueryFlow: MutableStateFlow<MovieListType> = MutableStateFlow(MovieListType.POPULAR)

    val movieList = MediatorLiveData<MutableList<MovieDomain>>()

 /*   val popularMoviesFlow: Flow<PagingData<MovieDomain>> =
        movieRepository.popularMoviesFlow.cachedIn(viewModelScope)*/

    fun getMoviesFlow(movieListType: MovieListType): Flow<PagingData<MovieDomain>> {
        return movieRepository.popularMoviesFlow(movieListType).cachedIn(viewModelScope)
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


/*
private val page = MutableLiveData<Int>().apply { value = 1 }

private val _moviesList: LiveData<List<Movie>>
val movieList = MediatorLiveData<MutableList<Movie>>()

init {
    Timber.d("ShowALlViewModel")
    _moviesList = when (movieListType) {
        MovieListType.POPULAR -> {
            page.switchMap { page ->
                liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
                    emitSource(movieRepository.getMoviesList(page) {})
                }
            }
        }//TODO:
        else -> page.switchMap {
            liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {

            }
        }
    }

    movieList.addSource(_moviesList) {
        it?.let {  movieList->
            this.movieList.appendList(movieList)

        }

    }


}

fun loadMoreMovies() {
    page.value = page.value?.plus(1)
}

override val goToMovieDetailsEvent: MutableLiveData<Event<Movie>>
    get() = TODO("Not yet implemented")*/
