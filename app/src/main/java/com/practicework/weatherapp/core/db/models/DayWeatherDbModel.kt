package com.practicework.weatherapp.core.db.models

data class DayWeatherDbModel(
    val id: Long,
    val icon: String,
    val temp: String,
    val dayName: String
) {
    companion object {
        const val UNKNOWN_ICON = "unknown icon"
    }
}
