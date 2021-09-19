package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.response.BaseListResponse
import com.example.myapplication.extension.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.myapplication.extension.onFailure
import com.example.myapplication.extension.onException
import com.example.myapplication.extension.onSuccess

import retrofit2.Call

/**
 * Created by Ahmad Sedeek on 9/19/2021.
 */
abstract class BaseRepository {

    protected suspend fun <Response : BaseListResponse<ListType>, ListType> loadPageListCall(
        call: () -> Call<Response>,
        result: MutableLiveData<List<ListType>>,
        errorText: (String) -> Unit
    ) =
        withContext(Dispatchers.IO) {
            call().request { response ->
                response.onSuccess { data?.let { result.postValue((it).results) } }
                response.onException { message?.let { errorText(it) } }
                response.onFailure { message?.let { errorText(it) } }
            }
            result.apply { postValue(null) }
        }
}