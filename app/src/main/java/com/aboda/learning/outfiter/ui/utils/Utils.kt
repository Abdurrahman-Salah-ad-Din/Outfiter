package com.aboda.learning.outfiter.ui.utils

import android.view.View
import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.Clothes
import java.text.SimpleDateFormat
import java.util.*

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

fun getAllTShirts() = listOf(
    Clothes(
        R.drawable.cold_days_one,
        "Silver Baseball with Light olive Sweet Shirt", "Cold"
    ),
    Clothes(
        R.drawable.cold_days_two,
        "Yellow Jacket with yellow polo TShirt", "Cold"
    ),
    Clothes(
        R.drawable.cloudy_one,
        "Dark Light Olive Sweet Shirt", "Partly cloudy"
    ),
    Clothes(
        R.drawable.cloudy_two,
        "Black And Silver Sweet Shirt", "Cloudy"
    ),
    Clothes(
        R.drawable.cloudy_three,
        "Light Heavy Olive Sweet Shirt", "Mostly cloudy"
    ),
    Clothes(
        R.drawable.sunny_one,
        "Dark Navy Blue Pullover", "Sunny"
    ),
    Clothes(
        R.drawable.sunny_two,
        "Light Heavy Olive Pullover", "Sunny"
    )
)

fun getAllPants() = listOf(
    Clothes(
        R.drawable.pants_one,
        "Relaxed Light Fit Jeans Pants", null
    ),
    Clothes(
        R.drawable.pants_two,
        "Relaxed Dark fit Jeans Pants", null
    ),
    Clothes(
        R.drawable.pants_three,
        "Beige Gabardine Pants", null
    )
)