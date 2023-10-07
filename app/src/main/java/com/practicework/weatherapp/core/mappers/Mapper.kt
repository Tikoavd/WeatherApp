package com.practicework.weatherapp.core.mappers

fun interface Mapper<T, R> : (T) -> R

interface Mappable<R> {
    fun map(): R
}