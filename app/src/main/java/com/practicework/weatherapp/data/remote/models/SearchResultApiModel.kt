package com.practicework.weatherapp.data.remote.models

import com.google.gson.annotations.SerializedName
import com.practicework.weatherapp.core.mappers.Mappable
import com.practicework.weatherapp.core.mappers.Mapper
import com.practicework.weatherapp.domain.models.SearchResult
import kotlin.random.Random

data class SearchResultApiModel(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?
) : Mappable<SearchResult> {
    override fun map(): SearchResult =
        SearchResult(
            id = Random.nextInt(0, 1342) * (id ?: 161718),
            name = name.orEmpty(),
            region = region.orEmpty()
        )
}
