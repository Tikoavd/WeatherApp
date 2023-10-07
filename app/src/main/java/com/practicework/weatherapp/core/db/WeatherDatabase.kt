package com.practicework.weatherapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practicework.weatherapp.core.db.converters.WeatherConverter
import com.practicework.weatherapp.data.local.FullWeatherDao
import com.practicework.weatherapp.core.db.models.FullWeatherInfoEntity

@Database(entities = [FullWeatherInfoEntity::class], version = 1)
@TypeConverters(*[WeatherConverter::class])
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun fullWeatherDao(): FullWeatherDao
}

const val DB_NAME = "weather_db"
