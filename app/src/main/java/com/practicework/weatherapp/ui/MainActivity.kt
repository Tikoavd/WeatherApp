package com.practicework.weatherapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.practicework.weatherapp.ui.homepage.HomeScreen
import com.practicework.weatherapp.ui.theme.WeatherAppStyle
import com.practicework.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkModeValue = isSystemInDarkTheme()
            val currentStyle by remember { mutableStateOf(WeatherAppStyle.Main) }
            val isDarkMode by remember { mutableStateOf(isDarkModeValue) }
            val systemUiController = rememberSystemUiController()

            if (isDarkMode) {
                systemUiController.setSystemBarsColor(color = Color.Transparent)
            } else {
                systemUiController.setSystemBarsColor(color = Color.Black)
            }

            WeatherAppTheme(
                darkTheme = isDarkMode,
                style = currentStyle
            ){
                HomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}