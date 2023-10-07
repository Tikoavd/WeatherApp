package com.practicework.weatherapp.ui.search_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.practicework.weatherapp.domain.models.SearchResult
import com.practicework.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun SearchCityItemScreen(
    modifier: Modifier,
    data: SearchResult,
    onItemClickedListener: ((SearchResult) -> Unit)? = null
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(24.dp)
        .clickable { onItemClickedListener?.invoke(data)},
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${data.name} - ",
            style = WeatherAppTheme.typography.labelLargeBold
                .copy(color = WeatherAppTheme.colors.primaryTextFieldColor),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = data.region,
            style = WeatherAppTheme.typography.labelLargeMedium
                .copy(color = WeatherAppTheme.colors.primaryTextFieldColor),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}