package com.example.myapplication.ui.view_all

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
 * Created by Ahmad Sedeek on 9/20/2021.
 */

@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    movieListType: MovieListType
) : ViewModel() ,GoToMovie{

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
        get() = TODO("Not yet implemented")

}