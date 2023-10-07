package com.practicework.weatherapp.ui.search_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.practicework.weatherapp.R
import com.practicework.weatherapp.domain.models.SearchResult
import com.practicework.weatherapp.presentation.HomeScreenEvent
import com.practicework.weatherapp.ui.theme.WeatherAppTheme


@Composable
fun TopSheetScreen(
    modifier: Modifier,
    searchValue: String,
    cityList: List<SearchResult>,
    send: (HomeScreenEvent) -> Unit
) {
    val hintText = stringResource(id = R.string.search_city)
    var isShowCity by rememberSaveable {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier,
        shape = WeatherAppTheme.roundedCornerShape.shapeMedium,
        backgroundColor = WeatherAppTheme.colors.secondaryBackground

    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 32.dp, top = 27.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    modifier = Modifier
                        .size(width = 24.dp, height = 24.dp)
                        .clickable { send(HomeScreenEvent.SearchClosed) }
                        .testTag("back"),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null
                )
                Spacer(
                    modifier = Modifier
                        .width(10.dp)
                )

                BasicTextField(
                    modifier = Modifier.testTag("search_field"),
                    value = searchValue,
                    onValueChange = { input ->
                        send(HomeScreenEvent.SearchChanged(input))
                        isShowCity = input.isNotEmpty()
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(onSearch = {
                        send(HomeScreenEvent.SearchChanged(""))
                    }),
                    textStyle = WeatherAppTheme.typography.textFieldMedium
                        .copy(color = WeatherAppTheme.colors.primaryTextFieldColor),
                    cursorBrush = Brush.linearGradient(
                        listOf(
                            WeatherAppTheme.colors.primaryTextFieldColor,
                            WeatherAppTheme.colors.primaryTextFieldColor
                        )
                    ),
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .defaultMinSize(minHeight = 55.dp)
                                .wrapContentHeight()
                                .border(
                                    1.dp, WeatherAppTheme.colors.primaryTextFieldColor,
                                    RoundedCornerShape(15.dp)
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        start = 13.dp,
                                        top = 16.dp,
                                        bottom = 15.dp,
                                        end = 13.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                if (searchValue.isEmpty()) {
                                    Text(
                                        style = WeatherAppTheme
                                            .typography.textFieldRegular
                                            .copy(color = WeatherAppTheme.colors.primaryHintColor),
                                        text = hintText
                                    )
                                } else {
                                    innerTextField.invoke()
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Image(
                                        modifier = Modifier
                                            .clickable {
                                                send(HomeScreenEvent.SearchChanged(""))
                                            }
                                            .size(width = 24.dp, height = 24.dp),
                                        painter = painterResource(id = R.drawable.ic_clear),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                )
            }
            if (isShowCity) {
                SearchCityContainerScreen(
                    modifier = Modifier
                        .fillMaxWidth(),
                    data = cityList,
                    closeTopSheetSearchScreenCallBack = {
                        send(HomeScreenEvent.SearchClosed)
                    }
                ) {
                    send(HomeScreenEvent.SearchChecked(it))
                }
            }
        }
    }
}