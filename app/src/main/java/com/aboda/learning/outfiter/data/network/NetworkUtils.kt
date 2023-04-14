package com.aboda.learning.outfiter.data.network

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object NetworkUtils {
    const val SCHEME = "https"
    const val HOST = "api.open-meteo.com"
    const val FIRST_PATH = "v1"
    const val SECOND_PATH = "forecast"
    const val NUMBER_OF_DAILY_WEATHER = 10
    const val NUMBER_OF_HOURLY_WEATHER = 24
}

fun getStartDate(): String = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

fun getDateAfterTenDays(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_MONTH, 10)
    val dateAfterTenDays = calendar.time
    return dateFormat.format(dateAfterTenDays)
}