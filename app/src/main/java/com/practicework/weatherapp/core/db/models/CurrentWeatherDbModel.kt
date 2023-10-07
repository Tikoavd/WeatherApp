package com.practicework.weatherapp.core.db.models

data class CurrentWeatherDbModel(
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
