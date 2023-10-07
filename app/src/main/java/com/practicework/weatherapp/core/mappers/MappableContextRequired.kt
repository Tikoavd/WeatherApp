package com.practicework.weatherapp.core.mappers

import android.content.Context

interface MappableContextRequired<T> {
    fun map(context: Context): T
}