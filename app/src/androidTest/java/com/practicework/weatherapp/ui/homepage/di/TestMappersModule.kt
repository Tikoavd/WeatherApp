package com.practicework.weatherapp.ui.homepage.di

import android.content.Context
import com.practicework.weatherapp.core.db.models.FullWeatherInfoEntity
import com.practicework.weatherapp.core.db.models.LocationInfoDbModel
import com.practicework.weatherapp.core.mappers.Mapper
import com.practicework.weatherapp.data.mapper.fullWeatherFromDbMapper
import com.practicework.weatherapp.data.mapper.fullWeatherToDbMapper
import com.practicework.weatherapp.data.mapper.locationListMapper
import com.practicework.weatherapp.data.remote.models.FullWeatherApiModel
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.SearchResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TestMappersModule {

    @Provides
    fun provideFullWeatherMapper(@ApplicationContext context: Context)
            : Mapper<FullWeatherApiModel, FullWeatherInfo> {
        return Mapper { input -> input.map(context) }
    }

    @Provides
    fun provideFullWeatherToDbMapper(): Mapper<FullWeatherInfo, FullWeatherInfoEntity> = fullWeatherToDbMapper

    @Provides
    fun provideFullWeatherFromDbMapper(): Mapper<FullWeatherInfoEntity, FullWeatherInfo> = fullWeatherFromDbMapper

    @Provides
    fun provideLocationListMapper(): Mapper<List<LocationInfoDbModel>, List<SearchResult>> = locationListMapper
}