package com.practicework.weatherapp.domain.repository

import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getWeather(location: String) : Flow<Resource<FullWeatherInfo>>
    fun getSearchResult(search: String) : Flow<Resource<List<SearchResult>>>
}