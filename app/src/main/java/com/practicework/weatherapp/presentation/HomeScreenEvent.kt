package com.practicework.weatherapp.presentation

import com.practicework.weatherapp.domain.models.SearchResult

sealed interface HomeScreenEvent {
    object SearchClicked : HomeScreenEvent
    object PermissionGranted : HomeScreenEvent
    object PermissionNotGranted : HomeScreenEvent
    object SearchClosed : HomeScreenEvent
    class SearchChecked(val searchResult: SearchResult) : HomeScreenEvent
    class SearchChanged(val input: String) : HomeScreenEvent
}