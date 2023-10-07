package com.practicework.weatherapp.domain.models

data class DayWeather(
    val id: Long,
    val icon: String,
    val temp: String,
    val dayName: String
) {
    companion object {
        const val UNKNOWN_ICON = "unknown icon"
    }
}
