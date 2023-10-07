package com.practicework.weatherapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class WeatherAppColors(
    val primaryBackground: Color,
    val secondaryBackground: Color,
    val thirdBackground:Color,
    val primaryTextColor: Color,
   val  secondaryTextColor:Color,
    val textStrokeColor: Color,
    val primaryTextFieldColor: Color,
    val primaryHintColor: Color,
    val tintColor:Color
)

data class WeatherAppTypography(
    val titleLarge: TextStyle,
    val titleLargeSmallWeight: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLargeBold: TextStyle,
    val labelLargeMedium: TextStyle,
    val labelLargeRegular: TextStyle,
    val labelMediumBold: TextStyle,
    val labelMediumRegular: TextStyle,
    val textFieldMedium:TextStyle,
    val textFieldRegular:TextStyle
)

data class WeatherAppRoundedCornerShape(
    val shapeLarge: RoundedCornerShape,
    val shapeMedium: RoundedCornerShape,
    val shapeSmall: RoundedCornerShape
)

object WeatherAppTheme {
    val colors: WeatherAppColors
        @Composable
        get() = LocalWeatherAppColors.current

    val typography: WeatherAppTypography
        @Composable
        get() = LocalWeatherAppTypography.current

    val roundedCornerShape: WeatherAppRoundedCornerShape
        @Composable
        get() = LocalWeatherAppRoundedCornerShape.current
}

val LocalWeatherAppColors = staticCompositionLocalOf<WeatherAppColors> {
    error("No colors provided")
}
val LocalWeatherAppTypography = staticCompositionLocalOf<WeatherAppTypography> {
    error("No font provided")
}

val LocalWeatherAppRoundedCornerShape =
    staticCompositionLocalOf<WeatherAppRoundedCornerShape> {
        error("No shape provided")
    }

enum class WeatherAppStyle {
    Main
}

