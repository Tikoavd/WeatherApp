package com.practicework.weatherapp.ui.homepage

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.SubcomposeAsyncImage
import com.practicework.weatherapp.R
import com.practicework.weatherapp.domain.models.CurrentWeather
import com.practicework.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun TemperatureInfoContainer(
    modifier: Modifier,
    currentWeather: CurrentWeather,
) {

    val icWindSpeedInfoRes = painterResource(id = R.drawable.ic_wind)
    val icHumidityInfoRes = painterResource(id = R.drawable.ic_droplet)

    ConstraintLayout(
        modifier = modifier
            .wrapContentWidth()
    ) {
        val (icWeatherState, tempInfo, tempTypeInfo, dayStateInfo,
            icWindSpeed, windSpeedInfo, icHumidity, humidityInfo) = createRefs()
        createHorizontalChain(tempInfo, tempTypeInfo, chainStyle = ChainStyle.Packed)
        SubcomposeAsyncImage(
            modifier = Modifier
                .size(height = 80.dp, width = 80.dp)
                .constrainAs(icWeatherState) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            model = currentWeather.icon,
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(
                            Alignment.Center
                        ),
                    color = WeatherAppTheme.colors.primaryTextFieldColor
                )
            }
        )

        Text(
            modifier = Modifier
                .constrainAs(tempInfo) {
                    start.linkTo(parent.start)
                    end.linkTo(tempTypeInfo.start)
                    top.linkTo(icWeatherState.bottom, 18.dp)
                },
            text = currentWeather.temp,
            style = WeatherAppTheme.typography.titleLarge,
            color = WeatherAppTheme.colors.primaryTextColor
        )

        Text(
            modifier = Modifier
                .constrainAs(dayStateInfo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(tempInfo.bottom, 18.dp)
                },
            text = currentWeather.description,
            style = WeatherAppTheme.typography.labelLargeRegular,
            color = WeatherAppTheme.colors.primaryTextColor,
        )

        Icon(
            modifier = Modifier
                .size(width = 20.dp, height = 20.dp)
                .constrainAs(icWindSpeed) {
                    start.linkTo(parent.start, 5.dp)
                    top.linkTo(dayStateInfo.bottom, 18.dp)
                },
            painter = icWindSpeedInfoRes,
            contentDescription = null,
            tint = WeatherAppTheme.colors.tintColor
        )

        Text(
            modifier = Modifier
                .constrainAs(windSpeedInfo) {
                    start.linkTo(icWindSpeed.end, 8.dp)
                    top.linkTo(icWindSpeed.top)
                    bottom.linkTo(icWindSpeed.bottom)
                }
                .alpha(0.9f),
            text = currentWeather.wind,
            style = WeatherAppTheme.typography.labelMediumRegular,
            color = WeatherAppTheme.colors.secondaryTextColor,
        )

        Icon(
            modifier = Modifier
                .size(width = 20.dp, height = 20.dp)
                .constrainAs(icHumidity) {
                    start.linkTo(windSpeedInfo.end, 40.dp)
                    top.linkTo(dayStateInfo.bottom, 18.dp)
                    end.linkTo(humidityInfo.start, 8.dp)
                },
            painter = icHumidityInfoRes,
            contentDescription = null,
            tint = WeatherAppTheme.colors.tintColor
        )

        Text(
            modifier = Modifier
                .constrainAs(humidityInfo) {
                    end.linkTo(parent.end, 5.dp)
                    top.linkTo(icHumidity.top)
                    start.linkTo(icHumidity.end)
                    bottom.linkTo(icHumidity.bottom)
                }
                .alpha(0.9f),
            text = currentWeather.humidity,
            style = WeatherAppTheme.typography.labelMediumRegular,
            color = WeatherAppTheme.colors.secondaryTextColor,
        )
    }
}