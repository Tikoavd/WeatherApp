package com.practicework.weatherapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class DayWeatherApiModel(
    @SerializedName("forecastday")
    val days: List<ForecastDayApiModel>?
)

data class ForecastDayApiModel(
    @SerializedName("date_epoch")
    val time: Long?,
    @SerializedName("day")
    val day: DayApiModel?
)

data class DayApiModel(
    @SerializedName("condition")
    val condition: ConditionApiModel?,
    @SerializedName("avgtemp_c")
    val temp_c: Double?,
    @SerializedName("avgtemp_f")
    val temp_f: Double?,
)
