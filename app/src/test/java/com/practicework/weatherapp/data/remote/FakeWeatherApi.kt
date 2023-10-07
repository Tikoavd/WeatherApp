package com.practicework.weatherapp.data.remote

import com.practicework.weatherapp.data.remote.models.ConditionApiModel
import com.practicework.weatherapp.data.remote.models.CurrentWeatherApiModel
import com.practicework.weatherapp.data.remote.models.DayWeatherApiModel
import com.practicework.weatherapp.data.remote.models.FullWeatherApiModel
import com.practicework.weatherapp.data.remote.models.LocationInfoApiModel
import com.practicework.weatherapp.data.remote.models.SearchResultApiModel
import com.practicework.weatherapp.data.remote.retrofit.WeatherApiService
import com.practicework.weatherapp.domain.models.CurrentWeather
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.LocationInfo
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class FakeWeatherApi : WeatherApiService {
    override suspend fun getWeather(
        key: String,
        location: String,
        days: Int
    ): Response<FullWeatherApiModel> =
        if (location.isNotBlank()) Response.success(
            FullWeatherApiModel(
                currentWeather = CurrentWeatherApiModel(
                    temp = 0.0,
                    wind = 0.0,
                    humidity = 1,
                    condition = ConditionApiModel("asd", "asd")
                ), dayWeather = DayWeatherApiModel(listOf()), location = LocationInfoApiModel(
                    name = "Rafael Little",
                    time = 0L,
                )
            )
        ) else Response.error(400, "".toResponseBody())

    override suspend fun getSearchResult(
        key: String,
        search: String
    ): Response<List<SearchResultApiModel>> =
        if (search.isNotBlank()) Response.success(emptyList())
        else Response.error(400, "".toResponseBody())
}
