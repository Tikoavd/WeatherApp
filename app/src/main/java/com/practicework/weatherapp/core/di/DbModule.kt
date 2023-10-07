package com.practicework.weatherapp.core.di

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
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideFullWeatherDao(db: WeatherDatabase): FullWeatherDao = db.fullWeatherDao()
}
