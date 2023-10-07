package com.practicework.weatherapp.data.remote

import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.data.remote.retrofit.WeatherApiService
import com.practicework.weatherapp.data.remote.source.WeatherRemoteDataSource
import com.practicework.weatherapp.data.remote.source.WeatherRemoteDataSourceImpl
import com.practicework.weatherapp.domain.models.CurrentWeather
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.LocationInfo
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class WeatherRemoteDataSourceImplTest {

    private lateinit var api: WeatherApiService
    private lateinit var remote: WeatherRemoteDataSource

    @Before
    fun setUp() {
        api = FakeWeatherApi()
        remote = WeatherRemoteDataSourceImpl(
            apiService = api,
            weatherMapper = { input ->
                FullWeatherInfo(
                    currentWeather = CurrentWeather(
                        icon = "",
                        temp = "",
                        description = "",
                        wind = "",
                        humidity = ""
                    ), dayWeather = listOf(),
                    location = LocationInfo(
                        name = "",
                        time = "",
                        date = ""
                    )
                )
            }
        )
    }

    @Test
    fun getWeather() {
        runBlocking {
            remote.getWeather("", "asd", 1).collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> assert(true)
                }
            }
        }
        runBlocking {
            remote.getWeather("", "", 1).catch{ assert(true) }.collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> fail()
                }
            }
        }
    }

    @Test
    fun getSearchResult() {
        runBlocking {
            remote.getSearchResult("","asd").collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> assert(true)
                }
            }
        }
        runBlocking {
            remote.getSearchResult("","").catch{ assert(true) }.collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> fail()
                }
            }
        }
    }
}