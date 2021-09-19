package com.example.myapplication.ui.home

import androidx.lifecycle.*
import com.example.myapplication.data.model.entity.Movie
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.extension.appendList
import com.example.myapplication.extension.liveDataBlockScope
import com.example.myapplication.util.Resource
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {


    //private val _loadPopularMovies = MutableLiveData<List<Movie>>()

    private val page = MutableLiveData<Int>().apply { value = 1 }
    private val loadPopularMovies: LiveData<List<Movie>>

    val popularMovieList = MediatorLiveData<MutableList<Movie>>()
    // get() = _loadPopularMovies.distinctUntilChanged()
    //get() = _loadPopularMovies.distinctUntilChanged()

    init {
        Timber.d("view Model initiated")

        loadPopularMovies = page.switchMap {
            liveDataBlockScope {
                movieRepository.getPopularMovies(it) {}
            }
        }

        popularMovieList.addSource(loadPopularMovies) { movies ->
            movies?.let { list ->
                popularMovieList.appendList(list)
            }
        }
    }


    fun loadMorePopular() {
        page.value = page.value?.plus(1)
    }

    /* private fun getPopularMovies() = viewModelScope.launch {

         _status.value = MarsApiStatus.LOADING
         try {
             _loadPopularMovies.value = movieRepository.getPopularMovies()
             _status.value = MarsApiStatus.DONE

         } catch (e: Exception) {
             _status.value = MarsApiStatus.ERROR
             _loadPopularMovies.value = ArrayList()
         }

     }*/

}

