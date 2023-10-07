package com.practicework.weatherapp.presentation

import android.util.Log
import androidx.compose.animation.SizeTransform
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicework.weatherapp.core.coroutines.CommonDispatchers
import com.practicework.weatherapp.core.locationprovider.LocationProviderClient
import com.practicework.weatherapp.core.safe_call_handler.Resource
import com.practicework.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val dispatchers: CommonDispatchers,
    private val locationProvider: LocationProviderClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState>
        get() = _uiState.asStateFlow()

    fun send(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.PermissionGranted -> getCurrentLocationWeather()
            HomeScreenEvent.PermissionNotGranted -> getWeather()
            is HomeScreenEvent.SearchChanged -> searchChanged(event.input)
            is HomeScreenEvent.SearchChecked -> {
                getWeather(event.searchResult.name)
                searchClosed()
            }
            HomeScreenEvent.SearchClicked -> searchClicked()
            HomeScreenEvent.SearchClosed -> searchClosed()
        }
    }

    private fun getCurrentLocationWeather() {
        viewModelScope.launch {
            locationProvider.getCurrentLocation { location ->
                getWeather("${location.latitude},${location.longitude}")
            }
        }
    }

    private fun getWeather(location: String = DEFAULT_LOCATION) {
        repository.getWeather(location)
            .onEach { resource ->
                when(resource) {
                    is Resource.Error -> {
                        _uiState.update { current ->
                            current.copy(isLoadingPage = false)
                        }
                    }
                    Resource.Loading -> {
                        _uiState.update { current ->
                            current.copy(isLoadingPage = true)
                        }
                    }
                    is Resource.Success -> {
                        _uiState.update { current ->
                            current.copy(isLoadingPage = false, fullWeatherInfo = resource.model)
                        }
                    }
                }
            }
            .flowOn(dispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

    private fun getSearchList(search: String) {
        repository.getSearchResult(search)
            .onEach { resource ->
                when(resource) {
                    is Resource.Error -> Unit
                    Resource.Loading -> Unit
                    is Resource.Success -> {
                        _uiState.update { current ->
                            current.copy(searchList = resource.model)
                        }
                    }
                }
            }
            .flowOn(dispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

    private fun searchChanged(input: String) {
        _uiState.update { current ->
            current.copy(searchInput = input)
        }
        getSearchList(input)
    }

    private fun searchClicked() {
        _uiState.update { current ->
            current.copy(searchClicked = true)
        }
    }

    private fun searchClosed() {
        _uiState.update { current ->
            current.copy(searchClicked = false, searchInput = "")
        }
    }

    companion object {
        const val DEFAULT_LOCATION = "New York"
    }
}