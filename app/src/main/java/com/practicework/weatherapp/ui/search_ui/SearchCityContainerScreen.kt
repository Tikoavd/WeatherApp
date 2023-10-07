package com.practicework.weatherapp.ui.search_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.practicework.weatherapp.domain.models.SearchResult
import com.practicework.weatherapp.ui.GRID_CELLS_FIX_COUNT
import com.practicework.weatherapp.ui.theme.WeatherAppTheme
import com.practicework.weatherapp.R

@Composable
fun SearchCityContainerScreen(
    modifier: Modifier,
    data: List<SearchResult>,
    closeTopSheetSearchScreenCallBack: (() -> Unit)? = null,
    onItemClickedListener: ((SearchResult) -> Unit)? = null
) {
    Column(modifier = modifier) {
        LazyVerticalGrid(
            modifier = modifier
                .heightIn(min = 100.dp, max = 230.dp)
                .padding(start = 32.dp, end = 32.dp, top = 39.dp, bottom = 24.dp),
            columns = GridCells.Fixed(GRID_CELLS_FIX_COUNT),
            verticalArrangement = Arrangement.spacedBy(25.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            items(items = data, key = { it.id }) { item ->
                SearchCityItemScreen(
                    modifier = Modifier.wrapContentSize(),
                    data = item,
                    onItemClickedListener = onItemClickedListener
                )
            }
        }

        Card(
            modifier = modifier
                .requiredHeight(38.dp)
                .clickable { closeTopSheetSearchScreenCallBack?.invoke() },
            shape = WeatherAppTheme.roundedCornerShape.shapeMedium,
            backgroundColor = WeatherAppTheme.colors.thirdBackground
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(width = 12.73.dp, height = 7.78.dp),
                    painter = painterResource(id = R.drawable.ic_up),
                    contentDescription = null
                )
            }
        }
    }
}