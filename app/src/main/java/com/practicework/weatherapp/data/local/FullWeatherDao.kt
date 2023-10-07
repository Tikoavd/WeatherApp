package com.practicework.weatherapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.practicework.weatherapp.core.db.models.FullWeatherInfoEntity
import com.practicework.weatherapp.core.db.models.LocationInfoDbModel
import retrofit2.http.DELETE

@Dao
interface FullWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: FullWeatherInfoEntity)

    @Query("DELETE FROM full_weather WHERE location LIKE :location")
    suspend fun deleteWeather(location: LocationInfoDbModel)

    @Query("SELECT * FROM full_weather WHERE location LIKE :location")
    suspend fun getWeather(location: LocationInfoDbModel): FullWeatherInfoEntity

    @Query("SELECT location FROM full_weather")
    suspend fun  searchLocations(): List<LocationInfoDbModel>
}