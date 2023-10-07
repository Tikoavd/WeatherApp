package com.practicework.weatherapp.data.local

import com.practicework.weatherapp.core.db.models.FullWeatherInfoEntity
import com.practicework.weatherapp.core.db.models.LocationInfoDbModel
import com.practicework.weatherapp.core.mappers.Mapper
import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.core.safe_call_handler.safeDbCall
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.SearchResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(
    private val fullWeatherDao: FullWeatherDao,
    private val fullWeatherToDbMapper: Mapper<FullWeatherInfo, FullWeatherInfoEntity>,
    private val fullWeatherFromDbMapper: Mapper<FullWeatherInfoEntity, FullWeatherInfo>,
    private val locationMapper: Mapper<List<LocationInfoDbModel>, List<SearchResult>>
) : WeatherLocalDataSource {

    override suspend fun insertWeather(weather: FullWeatherInfo) =
        fullWeatherDao.searchLocations().find { it.name == weather.location.name }
            ?.let { location ->
                fullWeatherDao.deleteWeather(location)
                fullWeatherDao.insertWeather(fullWeatherToDbMapper(weather))
            } ?: fullWeatherDao.insertWeather(fullWeatherToDbMapper(weather))

    override suspend fun getWeather(location: String): Flow<Resource<FullWeatherInfo>> {
        val locationInfo = fullWeatherDao.searchLocations().find { it.name == location }
            ?: return flowOf(Resource.Error(Exception(), null))
        return safeDbCall(fullWeatherFromDbMapper) {
            fullWeatherDao.getWeather(locationInfo)
        }
    }

    override suspend fun searchLocations(): Flow<Resource<List<SearchResult>>> =
        safeDbCall(locationMapper) {
            fullWeatherDao.searchLocations()
        }
}

