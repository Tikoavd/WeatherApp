package com.practicework.weatherapp.data.local

import com.practicework.weatherapp.core.db.models.CurrentWeatherDbModel
import com.practicework.weatherapp.core.db.models.FullWeatherInfoEntity
import com.practicework.weatherapp.core.db.models.LocationInfoDbModel
import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.data.mapper.fullWeatherFromDbMapper
import com.practicework.weatherapp.data.mapper.fullWeatherToDbMapper
import com.practicework.weatherapp.data.mapper.locationListMapper
import com.practicework.weatherapp.domain.models.CurrentWeather
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.LocationInfo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class WeatherLocalDataSourceImplTest {

    private lateinit var dao: FakeWeatherDao
    private lateinit var local: WeatherLocalDataSource

    @Before
    fun setUp() {
        dao = FakeWeatherDao()
        local = WeatherLocalDataSourceImpl(
            fullWeatherDao = dao,
            fullWeatherToDbMapper = fullWeatherToDbMapper,
            fullWeatherFromDbMapper = fullWeatherFromDbMapper,
            locationMapper = locationListMapper,
        )
    }

    @Test
    fun `weather local data source full testing`() {
        val weather = FullWeatherInfo(
            location = LocationInfo(
                name = "Angel Cherry",
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
        assertTrue(dao.data.contains(fullWeatherToDbMapper(weather)))
        runBlocking {
            local.getWeather("Angel Cherry").collect { resource ->
                when (resource) {
                    Resource.Loading -> Unit
                    is Resource.Success -> assertTrue(true)
                    is Resource.Error -> fail()
                }
            }
        }

        runBlocking {
            local.searchLocations().collect { resource ->
                when (resource) {
                    Resource.Loading -> Unit
                    is Resource.Success -> assertTrue(dao.data.map { it.location.name }
                        .containsAll(resource.model.map { it.name }))

                    is Resource.Error -> fail()
                }
            }
        }
    }
}