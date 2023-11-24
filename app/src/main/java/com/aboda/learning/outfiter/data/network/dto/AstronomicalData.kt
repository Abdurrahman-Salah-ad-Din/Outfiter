package com.aboda.learning.outfiter.data.network.dto

import com.google.gson.annotations.SerializedName

data class AstronomicalData(
    @SerializedName("is_moon_up")
    val isMoonUp: Int,
    @SerializedName("is_sun_up")
    val isSunUp: Int,
    @SerializedName("moon_illumination")
    val moonIllumination: Int,
    @SerializedName("moon_phase")
    val moonPhase: String,
    @SerializedName("moonrise")
    val moonRiseTime: String,
    @SerializedName("moonset")
    val moonSetTime: String,
    @SerializedName("sunrise")
    val sunRiseTime: String,
    @SerializedName("sunset")
    val sunSetTime: String
)