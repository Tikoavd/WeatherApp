package com.practicework.weatherapp.data.source

import com.practicework.weatherapp.BuildConfig
import com.practicework.weatherapp.core.locationprovider.LocationProviderClient
import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.data.local.WeatherLocalDataSource
import com.practicework.weatherapp.data.remote.source.WeatherRemoteDataSource
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.SearchResult
import com.practicework.weatherapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) : WeatherRepository {

    override fun getWeather(location: String): Flow<Resource<FullWeatherInfo>> {
        return remoteDataSource.getWeather(
            BuildConfig.API_KEY,
            location,
            3
        ).map { resource ->
            when (resource) {
                Resource.Loading -> resource

                is Resource.Success -> resource.also {
                    localDataSource.insertWeather(resource.model)
                }

                is Resource.Error -> resource
            }
        }.catch {
            emitAll(localDataSource.getWeather(location))
        }
    }

    override fun getSearchResult(search: String): Flow<Resource<List<SearchResult>>> {
        return remoteDataSource.getSearchResult(BuildConfig.API_KEY, search).catch {
            emitAll(localDataSource.searchLocations())
        }
    }
}