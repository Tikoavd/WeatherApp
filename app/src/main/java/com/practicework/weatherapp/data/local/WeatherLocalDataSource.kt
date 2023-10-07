package com.practicework.weatherapp.data.local

import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.LocationInfo
import com.practicework.weatherapp.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow

interface WeatherLocalDataSource {

    suspend fun insertWeather(weather: FullWeatherInfo)

    suspend fun getWeather(location: String): Flow<Resource<FullWeatherInfo>>

    suspend fun searchLocations(): Flow<Resource<List<SearchResult>>>
}