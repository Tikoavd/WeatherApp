package com.practicework.weatherapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class CurrentWeatherApiModel(
    @SerializedName("condition")
    val condition: ConditionApiModel?,
    @SerializedName("temp_f")
    val temp: Double?,
    @SerializedName("wind_mph")
    val wind: Double?,
    @SerializedName("humidity")
    val humidity: Int?
)

data class ConditionApiModel(
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("text")
    val description: String?,
)

