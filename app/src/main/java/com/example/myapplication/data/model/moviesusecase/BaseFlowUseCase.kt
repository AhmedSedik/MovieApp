package com.example.myapplication.data.model.moviesusecase


import com.example.myapplication.util.Resources
import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<in P, R>() {

//    suspend operator fun invoke(parameter: P, dataSourceResponse: DataSourceResponse<R>) {
//
//        execute(parameter).catch { e ->
//            dataSourceResponse.error(Exception(e))
//        }
//
//    }


    abstract fun execute(parameter: P): Flow<Resources<R>>


}