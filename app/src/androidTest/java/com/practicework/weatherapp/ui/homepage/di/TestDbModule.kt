package com.practicework.weatherapp.ui.homepage.di

import android.content.Context
import androidx.room.Room
import com.practicework.weatherapp.core.db.DB_NAME
import com.practicework.weatherapp.core.db.WeatherDatabase
import com.practicework.weatherapp.data.local.FullWeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            WeatherDatabase::class.java
        ).build()

    @Provides
    @Singleton
    fun provideFullWeatherDao(db: WeatherDatabase): FullWeatherDao = db.fullWeatherDao()
}
