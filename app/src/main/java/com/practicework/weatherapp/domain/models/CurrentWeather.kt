package com.practicework.weatherapp.domain.models

data class CurrentWeather(
    val icon: String,
    val temp: String,
    val description: String,
    val wind: String,
    val humidity: String
) {
    companion object {
        const val UNKNOWN_ICON = "unknown icon"
    }
}
