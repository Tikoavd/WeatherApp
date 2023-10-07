package com.practicework.weatherapp.domain.models

data class FullWeatherInfo(
    val currentWeather: CurrentWeather,
    val dayWeather: List<DayWeather>,
    val location: LocationInfo
)