package com.example.myapplication.data.model.moviesusecase

import com.example.myapplication.data.model.domain.MovieDetailsDomain
import com.example.myapplication.data.repository.MovieRepository
import com.example.myapplication.util.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

/**
 * Created by Ahmad Sedeek on 10/13/2021.
 */
class GetMovieDetailsUseCase @Inject constructor(
                    private val movieRepository: MovieRepository
):UseCase<GetMovieDetailsUseCase.Params,Flow<@kotlin.jvm.JvmSuppressWildcards Resources<MovieDetailsDomain> >> {

    data class Params(val movieId: Long)

    /*operator fun invoke(movieId: Long): Flow<Resources<MovieDetailsDomain>>{


    }*/

    override fun execute(params: Params): Flow<Resources<MovieDetailsDomain>> {
        return movieRepository.getMovieDetails(params.movieId).mapNotNull { response->
            val result = response.data

            return@mapNotNull when (response) {
                is Resources.Success-> Resources.Success(result!!)
                is Resources.Error -> Resources.Error(response.error,result)
                is Resources.Loading -> Resources.Loading(result)
            }
        }
    }


}