package com.practicework.weatherapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class LocationInfoApiModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("localtime_epoch")
    val time: Long
)
