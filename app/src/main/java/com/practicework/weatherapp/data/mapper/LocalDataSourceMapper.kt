package com.practicework.weatherapp.data.mapper

import com.practicework.weatherapp.core.db.models.CurrentWeatherDbModel
import com.practicework.weatherapp.core.db.models.DayWeatherDbModel
import com.practicework.weatherapp.core.db.models.FullWeatherInfoEntity
import com.practicework.weatherapp.core.db.models.LocationInfoDbModel
import com.practicework.weatherapp.core.mappers.Mapper
import com.practicework.weatherapp.domain.models.CurrentWeather
import com.practicework.weatherapp.domain.models.DayWeather
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.LocationInfo
import com.practicework.weatherapp.domain.models.SearchResult
import kotlin.random.Random

val fullWeatherToDbMapper: Mapper<FullWeatherInfo, FullWeatherInfoEntity> = Mapper { weather ->
    FullWeatherInfoEntity(
        location = LocationInfoDbModel(
            name = weather.location.name,
            time = weather.location.time,
            date = weather.location.date
        ), currentWeather = CurrentWeatherDbModel(
            icon = weather.currentWeather.icon,
            temp = weather.currentWeather.temp,
            description = weather.currentWeather.description,
            wind = weather.currentWeather.wind,
            humidity = weather.currentWeather.humidity
        ), dayWeather = weather.dayWeather.map {
            DayWeatherDbModel(
                id = it.id,
                icon = it.icon,
                temp = it.temp,
                dayName = it.dayName
            )
        }
    )
}
val fullWeatherFromDbMapper: Mapper<FullWeatherInfoEntity, FullWeatherInfo> = Mapper { weather ->
    FullWeatherInfo(
        location = LocationInfo(
            name = weather.location.name,
            time = weather.location.time,
            date = weather.location.date
        ), currentWeather = CurrentWeather(
            icon = weather.currentWeather.icon,
            temp = weather.currentWeather.temp,
            description = weather.currentWeather.description,
            wind = weather.currentWeather.wind,
            humidity = weather.currentWeather.humidity
        ), dayWeather = weather.dayWeather.map {
            DayWeather(
                id = it.id,
                icon = it.icon,
                temp = it.temp,
                dayName = it.dayName
            )
        }
    )
}
val locationListMapper: Mapper<List<LocationInfoDbModel>, List<SearchResult>> = Mapper { locationList ->
    locationList.map { location ->
        SearchResult(
            id = Random.nextLong(0, 1342) * 161718,
            name = location.name,
            region = location.name
        )
    }
}
