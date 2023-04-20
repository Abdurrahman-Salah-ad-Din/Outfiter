package com.aboda.learning.outfiter.data.local

import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.Clothes
import com.aboda.learning.outfiter.data.model.WeatherState

class ClothesData {

    val allTShirts = listOf(
        Clothes(
            R.drawable.cold_days_one,
            "Silver Baseball with Light olive Sweet Shirt", WeatherState.COLD
        ),
        Clothes(
            R.drawable.cold_days_two,
            "Yellow Jacket with yellow polo TShirt", WeatherState.COLD
        ),
        Clothes(
            R.drawable.cloudy_one,
            "Dark Light Olive Sweet Shirt", WeatherState.PARTLY_CLOUDY
        ),
        Clothes(
            R.drawable.cloudy_two,
            "Black And Silver Sweet Shirt", WeatherState.CLOUDY
        ),
        Clothes(
            R.drawable.cloudy_three,
            "Light Heavy Olive Sweet Shirt", WeatherState.MOSTLY_CLOUDY
        ),
        Clothes(
            R.drawable.sunny_one,
            "Dark Navy Blue Pullover", WeatherState.SUNNY
        ),
        Clothes(
            R.drawable.sunny_two,
            "Light Heavy Olive Pullover", WeatherState.SUNNY
        )
    )

    val allPants = listOf(
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
}