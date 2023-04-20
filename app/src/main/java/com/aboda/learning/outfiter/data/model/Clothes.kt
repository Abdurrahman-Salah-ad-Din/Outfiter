package com.aboda.learning.outfiter.data.model

import androidx.annotation.DrawableRes

data class Clothes(
    @DrawableRes val clothes: Int,
    val name: String,
    val state: WeatherState?
)
