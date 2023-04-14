package com.aboda.learning.outfiter.data.model

import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.network.NetworkUtils
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

fun parseDailyResponse(dailyResponse: String?): List<DailyWeather> {
    val json = JSONObject(dailyResponse!!)
    val hourly = json.getJSONObject("hourly")
    val daily = json.getJSONObject("daily")

    val data = mutableListOf<DailyWeather>()
    for (i in 0 until NetworkUtils.NUMBER_OF_DAILY_WEATHER) {
        val date = daily.getJSONArray("time").getString(i)
        val maxTemperature = daily.getJSONArray("temperature_2m_max").getDouble(i)
        val minTemperature = daily.getJSONArray("temperature_2m_min").getDouble(i)
        val sunrise = daily.getJSONArray("sunrise").getString(i)
        val sunset = daily.getJSONArray("sunset").getString(i)
        val ultraVioletIndex = daily.getJSONArray("uv_index_max").getDouble(i)
        val precipitationProbability = daily.getJSONArray("precipitation_probability_max").optInt(i)
        val windSpeed = daily.getJSONArray("windspeed_10m_max").getDouble(i)
        val hourlyData = mutableListOf<HourlyWeather>()
        for (j in (i * 24) until (i + 1) * NetworkUtils.NUMBER_OF_HOURLY_WEATHER) {
            val hour = hourly.getJSONArray("time").getString(j)
            val temperature = hourly.getJSONArray("temperature_2m").getDouble(j)
            val humidity = hourly.getJSONArray("relativehumidity_2m").getInt(j)
            val cloudCover = hourly.getJSONArray("cloudcover").getInt(j)
            val hourlyPrecipitationProbability =
                hourly.getJSONArray("precipitation_probability").optInt(j)
            hourlyData.add(
                HourlyWeather(
                    hour, temperature, humidity, cloudCover, hourlyPrecipitationProbability,
                    getHourlyWeatherImage(
                        hour,
                        sunset,
                        sunrise,
                        cloudCover,
                        hourlyPrecipitationProbability
                    )
                )
            )
        }
        data.add(
            DailyWeather(
                date, hourlyData, maxTemperature, minTemperature, sunrise, sunset,
                ultraVioletIndex, precipitationProbability, windSpeed, getWeatherState(
                    hourlyData.sumOf { it.cloudCover },
                    maxTemperature,
                    minTemperature,
                    windSpeed
                ),
                getDailyWeatherImage(
                    sunset,
                    sunrise,
                    (hourlyData.sumOf { it.cloudCover }) / hourlyData.size,
                    precipitationProbability
                )
            )
        )
    }
    return data
}

private fun getWeatherState(
    cloudCover: Int, maxTemperature: Double, minTemperature: Double,
    windSpeed: Double
): String {
    val isCold = minTemperature < 10
    val isWindy = windSpeed >= 10
    return when {
        isCold -> "Cold"
        cloudCover <= 10 -> "Sunny"
        cloudCover <= 50 -> "Partly cloudy"
        cloudCover <= 90 -> "Mostly cloudy"
        maxTemperature >= 30 -> "Hot"
        isWindy -> "Windy"
        else -> "Cloudy"
    }
}

private fun getDailyWeatherImage(
    sunset: String,
    sunrise: String,
    cloudCover: Int,
    precipitationProbability: Int
): Int {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
    val currentTime = formatter.format(Date())
    val isDayTime = currentTime in sunrise..sunset
    val isCloudy = cloudCover > 50
    val isRainy = precipitationProbability >= 40

    return when {
        isDayTime && !isCloudy && !isRainy -> R.drawable.sun
        !isDayTime && !isCloudy && !isRainy -> R.drawable.moon
        isDayTime && isCloudy && !isRainy -> R.drawable.sun_cloud
        !isDayTime && isCloudy && !isRainy -> R.drawable.moon_cloud
        isDayTime && isCloudy && isRainy -> R.drawable.sun_cloud_rain
        !isDayTime && isCloudy && isRainy -> R.drawable.moon_cloud_rain
        else -> R.drawable.sun
    }
}


private fun getHourlyWeatherImage(
    time: String,
    sunset: String,
    sunRise: String,
    cloudCover: Int,
    precipitationProbability: Int
): Int {
    val isDayTime = time in sunRise..sunset
    val isCloudy = cloudCover > 50
    val isRainy = precipitationProbability >= 40

    return when {
        isDayTime && !isCloudy && !isRainy -> R.drawable.sun
        !isDayTime && !isCloudy && !isRainy -> R.drawable.moon
        isDayTime && isCloudy && !isRainy -> R.drawable.sun_cloud
        !isDayTime && isCloudy && !isRainy -> R.drawable.moon_cloud
        isDayTime && isCloudy && isRainy -> R.drawable.sun_cloud_rain
        !isDayTime && isCloudy && isRainy -> R.drawable.moon_cloud_rain
        else -> R.drawable.moon
    }
}