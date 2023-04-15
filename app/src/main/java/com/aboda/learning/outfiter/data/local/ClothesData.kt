package com.aboda.learning.outfiter.data.local

import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.Clothes

class ClothesData {

    val allTShirts = listOf(
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