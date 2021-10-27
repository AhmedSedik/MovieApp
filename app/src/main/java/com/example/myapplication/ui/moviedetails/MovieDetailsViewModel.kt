package com.example.myapplication.ui.moviedetails

import androidx.lifecycle.*
import com.example.myapplication.R
import com.example.myapplication.data.model.domain.MovieDetailsDomain
import com.example.myapplication.data.model.moviesusecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.myapplication.data.model.response.Result
import com.example.myapplication.util.Resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach

import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 10/13/2021.
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var movieId: Int = 0

    private val _errorMessage = MutableLiveData<Int?>()
    val errorMessage: LiveData<Int?> = _errorMessage


    fun setIdMovie(movieId: Int) {
        this.movieId = movieId
    }


   val movieDetails = savedStateHandle.getLiveData<Long>(STATE_ID_MOVIE).switchMap { movieId->
        getMovieDetailsUseCase.execute(
            GetMovieDetailsUseCase.Params(movieId)
        ).asLiveData()
   }


//TODO
    private fun determineErrorMessage(error: Resources.ErrorType?) = when (error) {
        is Resources.ErrorType.DatabaseError -> R.string.database_error
        is Resources.ErrorType.HttpError -> R.string.server_error
        is Resources.ErrorType.IOError -> R.string.connection_error
        is Resources.ErrorType.Unknown -> R.string.generic_error
        null -> null
    }

init {

}
    companion object {
        private const val STATE_ID_MOVIE = "movieId"
    }

}