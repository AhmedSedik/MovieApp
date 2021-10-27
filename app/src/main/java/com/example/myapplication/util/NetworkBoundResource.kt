package com.example.myapplication.util

import kotlinx.coroutines.flow.*
import timber.log.Timber

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resources.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resources.Success(it) }
        } catch (throwable: Exception) {
            Timber.d( "networkBoundResource: error->${throwable}")
            query().map {
                Resources.Error(throwable, it) }
        }
    } else {
        query().map {
            Resources.Success(it)
        }
    }

    emitAll(flow)
}