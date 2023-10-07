package com.practicework.weatherapp.data.source

import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.data.local.FakeWeatherDao
import com.practicework.weatherapp.data.local.WeatherLocalDataSource
import com.practicework.weatherapp.data.local.WeatherLocalDataSourceImpl
import com.practicework.weatherapp.data.mapper.fullWeatherFromDbMapper
import com.practicework.weatherapp.data.mapper.fullWeatherToDbMapper
import com.practicework.weatherapp.data.mapper.locationListMapper
import com.practicework.weatherapp.data.remote.FakeWeatherApi
import com.practicework.weatherapp.data.remote.retrofit.WeatherApiService
import com.practicework.weatherapp.data.remote.source.WeatherRemoteDataSource
import com.practicework.weatherapp.data.remote.source.WeatherRemoteDataSourceImpl
import com.practicework.weatherapp.domain.models.CurrentWeather
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.LocationInfo
import com.practicework.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {

    private lateinit var dao: FakeWeatherDao
    private lateinit var local: WeatherLocalDataSource
    private lateinit var api: WeatherApiService
    private lateinit var remote: WeatherRemoteDataSource
    private lateinit var repo: WeatherRepository

    @Before
    fun setUp() {
        dao = FakeWeatherDao()
        local = WeatherLocalDataSourceImpl(
            fullWeatherDao = dao,
            fullWeatherToDbMapper = fullWeatherToDbMapper,
            fullWeatherFromDbMapper = fullWeatherFromDbMapper,
            locationMapper = locationListMapper,
        )
        api = FakeWeatherApi()
        remote = WeatherRemoteDataSourceImpl(
            apiService = api,
            weatherMapper = { input ->
                FullWeatherInfo(
                    currentWeather = CurrentWeather(
                        icon = "malesuada",
                        temp = "purus",
                        description = "latine",
                        wind = "prodesset",
                        humidity = "tincidunt"
                    ), dayWeather = listOf(),
                    location = LocationInfo(
                        name = "Madelyn Petersen",
                        time = "ignota",
                        date = "iisque"
                    )
                )
            }
        )
        repo = WeatherRepositoryImpl(remote, local)
    }

    @Test
    fun getWeather() {
        val weather = FullWeatherInfo(
            location = LocationInfo(
                name = "    ",
                time = "ancillae",
                date = "tortor"
            ), currentWeather = CurrentWeather(
                icon = "scripta",
                temp = "mei",
                description = "invidunt",
                wind = "scripserit",
                humidity = "altera"
            ), dayWeather = listOf()
        )
        runBlocking {
            local.insertWeather(weather)
        }
        runBlocking {
            repo.getWeather("    ").collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> assert(weather.toString() == res.model.toString())
                }
            }
        }
        runBlocking {
            repo.getWeather("asd").collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> assert(
                        FullWeatherInfo(
                            currentWeather = CurrentWeather(
                                icon = "malesuada",
                                temp = "purus",
                                description = "latine",
                                wind = "prodesset",
                                humidity = "tincidunt"
                            ), dayWeather = listOf(),
                            location = LocationInfo(
                                name = "Madelyn Petersen",
                                time = "ignota",
                                date = "iisque"
                            )
                        ).toString() == res.model.toString()
                    )
                }
            }
        }
    }

    @Test
    fun getSearchResult() {
        val weather = FullWeatherInfo(
            location = LocationInfo(
                name = "    ",
                time = "ancillae",
                date = "tortor"
            ), currentWeather = CurrentWeather(
                icon = "scripta",
                temp = "mei",
                description = "invidunt",
                wind = "scripserit",
                humidity = "altera"
            ), dayWeather = listOf()
        )
        runBlocking {
            local.insertWeather(weather)
        }
        runBlocking {
            repo.getSearchResult("").collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> assert(res.model.isNotEmpty())
                }
            }
        }
        runBlocking {
            repo.getSearchResult("asd").collect { res ->
                when (res) {
                    is Resource.Error -> fail()
                    Resource.Loading -> Unit
                    is Resource.Success -> assert(res.model.isEmpty())
                }
            }
        }
    }
}