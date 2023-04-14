package com.aboda.learning.outfiter.data.model

import androidx.annotation.DrawableRes

data class HourlyWeather(
    val time: String,
    val temperature: Double,
    val humidity: Int,
    val cloudCover: Int,
    val precipitationProbability: Int?,
    @DrawableRes val HourlyWeatherState: Int
)