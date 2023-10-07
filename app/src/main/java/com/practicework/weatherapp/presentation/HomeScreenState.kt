package com.practicework.weatherapp.presentation

import com.practicework.weatherapp.domain.models.*

data class HomeScreenState(
    val fullWeatherInfo: FullWeatherInfo = FullWeatherInfo(
        CurrentWeather(CurrentWeather.UNKNOWN_ICON, "", "", "", ""),
        emptyList(),
        LocationInfo("", "", "")
    ),
    val searchList: List<SearchResult> = emptyList(),
    val searchClicked: Boolean = false,
    val searchInput: String = "",
    val isLoadingPage: Boolean = true,
)
