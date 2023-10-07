package com.practicework.weatherapp.core.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practicework.weatherapp.core.db.models.CurrentWeatherDbModel
import com.practicework.weatherapp.core.db.models.DayWeatherDbModel
import com.practicework.weatherapp.core.db.models.LocationInfoDbModel

object WeatherConverter {

    @TypeConverter
    fun currentWeatherToString(weather: CurrentWeatherDbModel): String = Gson().toJson(weather)

    @TypeConverter
    fun stringToCurrentWeather(string: String): CurrentWeatherDbModel =
        Gson().fromJson(string, CurrentWeatherDbModel::class.java)

    @TypeConverter
    fun dayWeatherToString(weather: List<DayWeatherDbModel>): String = Gson().toJson(weather)

    @TypeConverter
    fun stringToDayWeather(string: String): List<DayWeatherDbModel> = Gson().fromJson(
        string,
        object : TypeToken<List<DayWeatherDbModel>>() {}.type
    )

    @TypeConverter
    fun locationToString(weather: LocationInfoDbModel): String = Gson().toJson(weather)

    @TypeConverter
    fun stringToLocation(string: String): LocationInfoDbModel =
        Gson().fromJson(string, LocationInfoDbModel::class.java)
}