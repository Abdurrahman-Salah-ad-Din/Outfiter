package com.aboda.learning.outfiter.ui.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

const val ALL_DAILY_WEATHER = "ALL_DAILY_WEATHER"
const val TODAY = "TODAY"

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun formatTime(time: String, inputPattern: String, outputPattern: String): String {
    val inputFormat = SimpleDateFormat(
        inputPattern,
        Locale.getDefault()
    )
    val outputFormat = SimpleDateFormat(
        outputPattern,
        Locale.getDefault()
    )
    val date = inputFormat.parse(time)
    return outputFormat.format(date)
}