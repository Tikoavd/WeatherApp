package com.practicework.weatherapp.data.remote.source

import com.practicework.weatherapp.core.mappers.Mapper
import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.core.safe_call_handler.safeApiCacheableCall
import com.practicework.weatherapp.core.safe_call_handler.safeApiCall
import com.practicework.weatherapp.data.remote.models.FullWeatherApiModel
import com.practicework.weatherapp.data.remote.retrofit.WeatherApiService
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val apiService: WeatherApiService,
    private val weatherMapper: Mapper<FullWeatherApiModel, FullWeatherInfo>
) : WeatherRemoteDataSource {

    override fun getWeather(key: String, location: String, days: Int)
        : Flow<Resource<FullWeatherInfo>> {
        return safeApiCacheableCall(
            mapper = weatherMapper,
            body = { apiService.getWeather(key, location, days) }
        )
    }

    override fun getSearchResult(key: String, search: String): Flow<Resource<List<SearchResult>>> {
        return safeApiCacheableCall(
            mapper = { result -> result.map{ it.map() } },
            body = { apiService.getSearchResult(key, search) }
        )
    }
}