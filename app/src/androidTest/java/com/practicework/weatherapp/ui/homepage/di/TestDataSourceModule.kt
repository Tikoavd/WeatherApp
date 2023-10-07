package com.practicework.weatherapp.ui.homepage.di

import com.practicework.weatherapp.data.local.WeatherLocalDataSource
import com.practicework.weatherapp.data.local.WeatherLocalDataSourceImpl
import com.practicework.weatherapp.data.remote.source.WeatherRemoteDataSource
import com.practicework.weatherapp.data.remote.source.WeatherRemoteDataSourceImpl
import com.practicework.weatherapp.data.source.WeatherRepositoryImpl
import com.practicework.weatherapp.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TestDataSourcesModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: WeatherRemoteDataSourceImpl)
            : WeatherRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSource: WeatherLocalDataSourceImpl)
            : WeatherLocalDataSource
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class TestRepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(repoImpl: WeatherRepositoryImpl)
            : WeatherRepository
}