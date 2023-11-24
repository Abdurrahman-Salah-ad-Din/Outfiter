package com.aboda.learning.outfiter.data.network.dto

import com.google.gson.annotations.SerializedName

data class WeatherState(
    @SerializedName("code")
    val code: Int,
    @SerializedName("icon")
    val imageUrl: String,
    @SerializedName("text")
    val name: String
)