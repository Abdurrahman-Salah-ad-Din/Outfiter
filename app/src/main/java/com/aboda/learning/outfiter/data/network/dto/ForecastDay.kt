package com.aboda.learning.outfiter.data.network.dto

import com.google.gson.annotations.SerializedName

data class ForecastDay(
    @SerializedName("astro")
    val astronomicalData: AstronomicalData,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    @SerializedName("day")
    val dailyWeatherForecast: DailyWeatherForecast,
    @SerializedName("hour")
    val hourlyWeatherForecasts: List<HourlyWeatherForecast>
)