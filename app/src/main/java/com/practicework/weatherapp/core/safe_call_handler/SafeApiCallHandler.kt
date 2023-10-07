package com.practicework.weatherapp.core.safe_call_handler

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.UnknownHostException

inline fun <RESPONSE, RESULT> safeApiCall(
    crossinline mapper: (RESPONSE) -> RESULT,
    crossinline body: suspend () -> Response<RESPONSE>
): Flow<Resource<RESULT>> {
    return flow {
        emit(Resource.Loading)
        try {
            val response = body.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(mapper(it)))
                } ?: emit(Resource.Error(Exception(ErrorTypes.EMPTY_RESPONSE.message), null))
            } else {
                val exception = Exception(ErrorTypes.NOT_SUCCESSFULLY_REQUEST.message)
                emit(Resource.Error(exception, null))
            }
        } catch (t: Throwable) {
            val exception = when (t) {
                is UnknownHostException -> {
                    Exception(ErrorTypes.NO_INTERNET_ACCESS.message)
                }
                else -> {
                    Exception(ErrorTypes.COULD_NOT_FETCH.message)
                }
            }
            emit(Resource.Error(exception = exception, null))
        }
    }
}

inline fun <RESPONSE, RESULT> safeApiCacheableCall(
    crossinline mapper: (RESPONSE) -> RESULT,
    crossinline body: suspend () -> Response<RESPONSE>
): Flow<Resource<RESULT>> {
    return flow {
        emit(Resource.Loading)
        try {
            val response = body.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(mapper(it)))
                } ?: throw Exception(ErrorTypes.EMPTY_RESPONSE.message)
            } else {
                val exception = Exception(ErrorTypes.NOT_SUCCESSFULLY_REQUEST.message)
                throw exception
            }
        } catch (t: Throwable) {
            val exception = when (t) {
                is UnknownHostException -> {
                    Exception(ErrorTypes.NO_INTERNET_ACCESS.message)
                }
                else -> {
                    Exception(ErrorTypes.COULD_NOT_FETCH.message)
                }
            }
            throw exception
        }
    }
}