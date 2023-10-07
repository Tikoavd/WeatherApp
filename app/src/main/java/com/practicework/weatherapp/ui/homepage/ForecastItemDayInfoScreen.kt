package com.practicework.weatherapp.ui.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.practicework.weatherapp.domain.models.DayWeather
import com.practicework.weatherapp.ui.theme.WeatherAppTheme
import com.practicework.weatherapp.R

@Composable
fun ForecastItemDayInfoScreen(
    modifier: Modifier,
    data: DayWeather,
    isPreviewMode: Boolean = false
) {
    Column(
        modifier = modifier
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isPreviewMode) {
            Image(
                modifier = Modifier
                    .size(width = 32.dp, height = 32.dp),
                painter = painterResource(id = R.drawable.ic_day_info_preview),
                contentDescription = null
            )
        } else {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(width = 32.dp, height = 32.dp),
                contentDescription = null,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(
                                Alignment.Center
                            ),
                        color = WeatherAppTheme.colors.primaryTextFieldColor
                    )
                },
                model = data.icon
            )
        }


        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = data.temp,
            style = WeatherAppTheme.typography.labelMediumRegular,
            textAlign = TextAlign.Center,
            color = WeatherAppTheme.colors.primaryTextColor
        )
        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.wrapContentWidth(),
            text = data.dayName,
            style = WeatherAppTheme.typography.labelMediumBold,
            textAlign = TextAlign.Center,
            color = WeatherAppTheme.colors.primaryTextColor
        )
    }
}