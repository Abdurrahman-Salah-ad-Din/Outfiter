package com.aboda.learning.outfiter.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class HourlyWeather(
    val time: String,
    val temperature: Double,
    val humidity: Int,
    val cloudCover: Int,
    val precipitationProbability: Int?,
    @DrawableRes val HourlyWeatherState: Int
) : Parcelable
