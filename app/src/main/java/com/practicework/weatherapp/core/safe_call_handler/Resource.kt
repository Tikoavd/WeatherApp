package com.practicework.weatherapp.core.safe_call_handler

sealed class Resource<out T>{
    class Success<T>(val model: T): Resource<T>()
    class Error<T>(val exception: Exception, var model : T?): Resource<T>()
    object Loading: Resource<Nothing>()
}