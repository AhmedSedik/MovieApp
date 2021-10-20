package com.example.myapplication.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication.data.model.domain.MovieDetailsDomain
import com.example.myapplication.data.model.moviesusecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.myapplication.data.model.response.Result
import com.example.myapplication.util.Resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 10/13/2021.
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private var movieId: Int = 0



    private val _movieDetails = MutableStateFlow<MovieDetailsDomain?>(null)
    val movieDetails = _movieDetails.asStateFlow()

    fun setIdMovie(movieId: Int) {
        this.movieId = movieId
    }


    fun getMovieById(movieId: Long) = getMovieDetailsUseCase.invoke(movieId).asLiveData()

init {

}


}