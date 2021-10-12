package com.example.myapplication.data.model.moviesusecase

import com.example.myapplication.data.model.domain.MovieDomain
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.Resources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) :
    BaseFlowUseCase<Unit, List<MovieDomain>>() {

    override fun execute(parameter: Unit): Flow<Resources<List<MovieDomain>>> =
        movieRepository.getPopularMovies(1)
}