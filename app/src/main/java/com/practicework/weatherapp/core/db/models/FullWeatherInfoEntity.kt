package com.practicework.weatherapp.core.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "full_weather")
data class FullWeatherInfoEntity(
    @PrimaryKey(false)
    @ColumnInfo(name = "location")
    val location: LocationInfoDbModel,
    @ColumnInfo(name = "current_weather")
    val currentWeather: CurrentWeatherDbModel,
    @ColumnInfo(name = "day_weather")
    val dayWeather: List<DayWeatherDbModel>
)
