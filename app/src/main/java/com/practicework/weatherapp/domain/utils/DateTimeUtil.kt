package com.practicework.weatherapp.domain.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDateTimeFormatted(millis: Long, formatType: DateTimeFormats): String =
    try {
        val simpleDateFormat = SimpleDateFormat(formatType.format, Locale.ENGLISH)
        simpleDateFormat.format(millis * 1000)
    } catch (e: Exception) {
        ""
    }