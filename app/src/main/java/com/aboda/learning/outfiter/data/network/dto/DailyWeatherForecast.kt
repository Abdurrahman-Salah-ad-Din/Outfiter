package com.aboda.learning.outfiter.data.network.dto

import com.google.gson.annotations.SerializedName

data class DailyWeatherForecast(
    @SerializedName("avghumidity")
    val averageHumidity: Double,
    @SerializedName("avgtemp_c")
    val averageTemperatureC: Double,
    @SerializedName("avgtemp_f")
    val averageTemperatureF: Double,
    @SerializedName("avgvis_km")
    val averageVisibilityKm: Double,
    @SerializedName("avgvis_miles")
    val averageVisibilityMiles: Double,
    @SerializedName("condition")
    val weatherState: WeatherState,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Int,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int,
    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerializedName("maxtemp_c")
    val maxTemperatureC: Double,
    @SerializedName("maxtemp_f")
    val maxTemperatureF: Double,
    @SerializedName("maxwind_kph")
    val maxWindSpeedKph: Double,
    @SerializedName("maxwind_mph")
    val maxWindSpeedMph: Double,
    @SerializedName("mintemp_c")
    val minTemperatureC: Double,
    @SerializedName("mintemp_f")
    val minTemperatureF: Double,
    @SerializedName("totalprecip_in")
    val totalPrecipitationIn: Double,
    @SerializedName("totalprecip_mm")
    val totalPrecipitationMm: Double,
    @SerializedName("totalsnow_cm")
    val totalSnowCm: Double,
    @SerializedName("uv")
    val uv: Double
)