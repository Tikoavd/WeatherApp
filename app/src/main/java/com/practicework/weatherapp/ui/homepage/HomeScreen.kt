package com.practicework.weatherapp.ui.homepage

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.practicework.weatherapp.R
import com.practicework.weatherapp.presentation.HomeScreenEvent
import com.practicework.weatherapp.presentation.HomeScreenViewModel
import com.practicework.weatherapp.ui.search_ui.TopSheetScreen
import com.practicework.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier,
    vm: HomeScreenViewModel = hiltViewModel()
) {
    val homeState by vm.uiState.collectAsState()


    val scaffoldState = rememberScaffoldState()
    val backgroundImgRes = painterResource(id = R.mipmap.home_background)
    val icSearchRes = painterResource(id = R.drawable.ic_search)

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { grantedPermission ->

            for (entry in grantedPermission.entries) {
                when (entry.key) {
                    Manifest.permission.ACCESS_FINE_LOCATION -> {
                        if (entry.value) {
                            vm.send(HomeScreenEvent.PermissionGranted)
                        } else {
                            vm.send(HomeScreenEvent.PermissionNotGranted)
                        }
                    }
                }
            }
        }

    LaunchedEffect(key1 = Unit) {
        launcher.launch(
            getPermissionsArray()
        )
    }


    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState
    ) {

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (backgroundImg, backgroundTint, progressIndicator, watchInfo,
                searchIcon, locationInfo, dateOfTime, tempInfoContainer,
                weatherDaysInfoContainer, topSheetSearchCountry) = createRefs()

            Image(
                modifier = modifier
                    .constrainAs(backgroundImg) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                painter = backgroundImgRes,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )

            Box(
                modifier = modifier
                    .constrainAs(backgroundTint) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .background(color = WeatherAppTheme.colors.primaryBackground)
            )

            Icon(
                modifier = Modifier
                    .requiredWidth(24.dp)
                    .requiredHeight(24.dp)
                    .constrainAs(searchIcon) {
                        end.linkTo(parent.end, 32.dp)
                        top.linkTo(parent.top, 40.dp)
                    }
                    .clickable { vm.send(HomeScreenEvent.SearchClicked) },
                painter = icSearchRes,
                tint = WeatherAppTheme.colors.tintColor,
                contentDescription = "search",
            )

            Text(
                modifier = Modifier
                    .constrainAs(watchInfo) {
                        linkTo(
                            top = searchIcon.top,
                            bottom = searchIcon.bottom,
                            start = parent.start,
                            end = parent.end
                        )
                    },
                style = WeatherAppTheme.typography.titleSmall,
                color = WeatherAppTheme.colors.primaryTextColor,
                text = homeState.fullWeatherInfo.location.time
            )


            Text(
                modifier = Modifier
                    .constrainAs(locationInfo) {
                        top.linkTo(searchIcon.bottom, 64.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                text = homeState.fullWeatherInfo.location.name,
                style = WeatherAppTheme.typography.titleMedium,
                color = WeatherAppTheme.colors.primaryTextColor
            )

            Text(
                modifier = Modifier
                    .constrainAs(dateOfTime) {
                        top.linkTo(locationInfo.bottom, margin = 4.dp)
                        start.linkTo(locationInfo.start)
                        end.linkTo(locationInfo.end)
                    },
                text = homeState.fullWeatherInfo.location.date,
                style = WeatherAppTheme.typography.labelLargeRegular,
                color = WeatherAppTheme.colors.primaryTextColor
            )

            TemperatureInfoContainer(
                modifier = Modifier
                    .constrainAs(ref = tempInfoContainer) {
                        top.linkTo(dateOfTime.bottom, 96.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                currentWeather = homeState.fullWeatherInfo.currentWeather
            )

            WeatherDaysInfoContainer(
                modifier = Modifier
                    .requiredHeightIn(min = 72.dp, max = 80.dp)
                    .constrainAs(ref = weatherDaysInfoContainer) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        top.linkTo(tempInfoContainer.bottom)
                        end.linkTo(parent.end)
                    },
                data = homeState.fullWeatherInfo.dayWeather
            )
            if (homeState.searchClicked) {
                TopSheetScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 104.dp, max = 349.dp)
                        .constrainAs(topSheetSearchCountry) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    searchValue = homeState.searchInput,
                    cityList = homeState.searchList,
                    send = vm::send
                )
            }

            if (homeState.isLoadingPage) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(width = 60.dp, height = 60.dp)
                        .constrainAs(progressIndicator) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        },
                    color = WeatherAppTheme.colors.secondaryBackground
                )
            }
        }
    }
}

private fun getPermissionsArray(): Array<String> =
    arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION
    )


