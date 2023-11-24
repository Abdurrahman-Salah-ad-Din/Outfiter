package com.aboda.learning.outfiter.data.network.dto

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("current")
    val currentWeatherData: CurrentWeatherData,
    @SerializedName("forecast")
    val forecast: List<ForecastDay>,
    @SerializedName("location")
    val location: Location
)