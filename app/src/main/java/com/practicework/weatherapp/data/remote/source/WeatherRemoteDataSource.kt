package com.practicework.weatherapp.data.remote.source

import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface WeatherRemoteDataSource {

    fun getWeather(
        key: String,
        location: String,
        days: Int
    ) : Flow<Resource<FullWeatherInfo>>

    fun getSearchResult(
        key: String,
        search: String
    ) : Flow<Resource<List<SearchResult>>>
}