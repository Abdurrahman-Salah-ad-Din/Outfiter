package com.aboda.learning.outfiter.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class DailyWeather(
    val dayDate: String,
    val dayHours: List<HourlyWeather>,
    val maxTemperature: Double,
    val minTemperature: Double,
    val sunRise: String,
    val sunSet: String,
    val UVIndex: Double,
    val precipitationProbability: Int?,
    val windSpeed: Double,
    val stringState: String,
    @DrawableRes val dailyWeatherState: Int
) : Parcelable
