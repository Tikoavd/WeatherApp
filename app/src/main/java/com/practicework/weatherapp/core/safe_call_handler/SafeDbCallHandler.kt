package com.practicework.weatherapp.core.safe_call_handler

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

inline fun <DBMODEL, MODEL> safeDbCall(
    crossinline mapper: (DBMODEL) -> MODEL,
    crossinline body: suspend () -> DBMODEL
): Flow<Resource<MODEL>> = flow {
    emit(Resource.Loading)
    emit(
        try {
            body()?.let {
                Resource.Success(mapper(it))
            } ?: Resource.Error(Exception(), null)
        } catch (e: Exception) {
            Resource.Error(e, null)
        }
    )
}
