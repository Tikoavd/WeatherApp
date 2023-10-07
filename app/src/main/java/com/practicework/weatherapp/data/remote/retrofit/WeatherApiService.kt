package com.practicework.weatherapp.data.remote.retrofit

import com.practicework.weatherapp.data.remote.models.FullWeatherApiModel
import com.practicework.weatherapp.data.remote.models.SearchResultApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(ApiConfig.GET_WEATHER_PATH)
    suspend fun getWeather(
        @Query(ApiConfig.KEY) key: String,
        @Query(ApiConfig.LOCATION) location: String,
        @Query(ApiConfig.DAYS) days: Int
    ) : Response<FullWeatherApiModel>

    @GET(ApiConfig.GET_SEARCH_PATH)
    suspend fun getSearchResult(
        @Query(ApiConfig.KEY) key: String,
        @Query(ApiConfig.SEARCH) search: String,
    ) : Response<List<SearchResultApiModel>>
}