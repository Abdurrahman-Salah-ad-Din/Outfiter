package com.aboda.learning.outfiter.data.network

import com.aboda.learning.outfiter.data.model.DailyWeather

interface NetworkService {
    fun getAllDailyWeather(allDailyWeather: List<DailyWeather>)
}