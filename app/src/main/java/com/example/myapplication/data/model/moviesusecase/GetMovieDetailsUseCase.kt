package com.example.myapplication.data.model.moviesusecase

import com.example.myapplication.data.model.domain.MovieDetailsDomain
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.Resources
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 10/13/2021.
 */
class GetMovieDetailsUseCase @Inject constructor(
                    private val movieRepository: MovieRepository
): BaseFlowUseCase<Unit, MovieDetailsDomain>() {
    override fun execute(parameter: Unit): Flow<Resources<MovieDetailsDomain>> {
        TODO("Not yet implemented")
    }

     operator fun invoke(movieId: Long): Flow<Resources<MovieDetailsDomain>>{

         return movieRepository.getMovieDetails(movieId)
     }

}