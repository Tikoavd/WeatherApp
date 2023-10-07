package com.practicework.weatherapp.data.local

import com.practicework.weatherapp.core.db.models.FullWeatherInfoEntity
import com.practicework.weatherapp.core.db.models.LocationInfoDbModel

class FakeWeatherDao : FullWeatherDao {
    val data = mutableListOf<FullWeatherInfoEntity>()

    override suspend fun insertWeather(weather: FullWeatherInfoEntity) {
        data.add(weather)
    }

    override suspend fun deleteWeather(location: LocationInfoDbModel) {
        val predicate = data.filter { it.location == location }
        data.removeAll(predicate)
    }

    override suspend fun getWeather(location: LocationInfoDbModel): FullWeatherInfoEntity {
        return data.find { it.location == location } ?: throw NoSuchFieldException()
    }

    override suspend fun searchLocations(): List<LocationInfoDbModel> =
        data.map { it.location }
}