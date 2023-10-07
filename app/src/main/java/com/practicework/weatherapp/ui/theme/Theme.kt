package com.practicework.weatherapp.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun WeatherAppTheme(
    style: WeatherAppStyle = WeatherAppStyle.Main,
    darkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                WeatherAppStyle.Main -> baseDarkPalette
            }
        }
        false -> {
            when (style) {
                WeatherAppStyle.Main -> baseLightPalette
            }
        }
    }

    CompositionLocalProvider(
        LocalWeatherAppColors provides colors,
        LocalWeatherAppTypography provides typography,
        LocalWeatherAppRoundedCornerShape provides shapes,
        content = content
    )
}