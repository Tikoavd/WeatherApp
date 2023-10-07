package com.practicework.weatherapp.ui.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practicework.weatherapp.domain.models.DayWeather
import com.practicework.weatherapp.ui.GRID_CELLS_FIX_COUNT

@Composable
fun WeatherDaysInfoContainer(
    modifier: Modifier,
    data: List<DayWeather>) {

    LazyHorizontalGrid(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(51.dp),
        rows = GridCells.Fixed(GRID_CELLS_FIX_COUNT),
    ) {
        items(items = data, key = { it.id }) { item ->
            ForecastItemDayInfoScreen(
                modifier = Modifier
                    .wrapContentSize(),
                data = item
            )
        }
    }
}