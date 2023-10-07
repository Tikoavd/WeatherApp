package com.practicework.weatherapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

val shapes = WeatherAppRoundedCornerShape(
    shapeLarge = RoundedCornerShape(40.dp),
    shapeMedium = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 0.dp,
        bottomEnd = 30.dp,
        bottomStart = 30.dp
    ),
    shapeSmall = RoundedCornerShape(20.dp)
)