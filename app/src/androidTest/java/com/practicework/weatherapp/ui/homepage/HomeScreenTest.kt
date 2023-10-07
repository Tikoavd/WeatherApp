package com.practicework.weatherapp.ui.homepage

import android.Manifest
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.rule.GrantPermissionRule
import com.practicework.weatherapp.core.di.DbModule
import com.practicework.weatherapp.core.di.NetworkModule
import com.practicework.weatherapp.data.remote.retrofit.WeatherApiService
import com.practicework.weatherapp.di.ApiServiceModule
import com.practicework.weatherapp.di.DataSourcesModule
import com.practicework.weatherapp.di.MappersModule
import com.practicework.weatherapp.di.RepositoryModule
import com.practicework.weatherapp.ui.MainActivity
import com.practicework.weatherapp.ui.theme.WeatherAppStyle
import com.practicework.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@HiltAndroidTest
@UninstallModules(
    DbModule::class,
    NetworkModule::class,
    ApiServiceModule::class,
    DataSourcesModule::class,
    MappersModule::class,
    RepositoryModule::class
)
class HomeScreenTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val grantPermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun click_search_button_is_opening_search_ui() {
        composeRule.onNodeWithContentDescription("search").performClick()
        composeRule.onNodeWithTag("search_field").assertIsDisplayed()
        composeRule.onNodeWithTag("search_field").performTextInput("Yerevan")
        composeRule.onNodeWithTag("back").performClick()
        composeRule.onNodeWithTag("search_field").assertDoesNotExist()
    }
}
