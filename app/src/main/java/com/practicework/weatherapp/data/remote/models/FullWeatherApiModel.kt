package com.practicework.weatherapp.data.remote.models

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.practicework.weatherapp.R
import com.practicework.weatherapp.core.mappers.MappableContextRequired
import com.practicework.weatherapp.domain.models.CurrentWeather
import com.practicework.weatherapp.domain.models.DayWeather
import com.practicework.weatherapp.domain.models.FullWeatherInfo
import com.practicework.weatherapp.domain.models.LocationInfo
import com.practicework.weatherapp.domain.utils.DateTimeFormats
import com.practicework.weatherapp.domain.utils.getDateTimeFormatted

data class FullWeatherApiModel(
    @SerializedName("current")
    val currentWeather: CurrentWeatherApiModel?,
    @SerializedName("forecast")
    val dayWeather: DayWeatherApiModel?,
    @SerializedName("location")
    val location: LocationInfoApiModel?
) : MappableContextRequired<FullWeatherInfo> {

    companion object {
        const val HTTPS = "https:"
    }

    override fun map(context: Context): FullWeatherInfo =
        FullWeatherInfo(
            currentWeather = CurrentWeather(
                icon = currentWeather?.condition?.icon
                    ?.let { "$HTTPS$it" } ?: CurrentWeather.UNKNOWN_ICON,
                temp = "${currentWeather?.temp ?: 0.0}°F",
                description = String.format(
                    context.getString(R.string.day_info_format),
                    currentWeather?.condition?.description.orEmpty()
                ),
                wind = String.format(
                    context.getString(R.string.mph_format),
                    currentWeather?.wind ?: 0.0
                ),
                humidity = String.format(
                    context.getString(R.string.humidity_format),
                    currentWeather?.humidity ?: 0
                )
            ),
            dayWeather = dayWeather?.days?.mapIndexed { index, model ->
                DayWeather(
                    id = model.time ?: 0,
                    icon = model.day?.condition?.icon
                        ?.let { "$HTTPS$it" } ?: DayWeather.UNKNOWN_ICON,
                    temp = "${model.day?.temp_c ?: 0.0}°/${model.day?.temp_f ?: 0.0}°F",
                    dayName = when (index) {
                        0 -> context.getString(R.string.today)
                        1 -> context.getString(R.string.tomorrow)
                        else -> {
                            getDateTimeFormatted(model.time ?: 0, DateTimeFormats.DAY_FORMAT)
                        }
                    },
                )
            } ?: emptyList(),
            location = LocationInfo(
                name = location?.name.orEmpty(),
                time = getDateTimeFormatted(location?.time ?: 0, DateTimeFormats.TIME_FORMAT),
                date = getDateTimeFormatted(location?.time ?: 0, DateTimeFormats.DATE_FORMAT)
            )
        )
}
