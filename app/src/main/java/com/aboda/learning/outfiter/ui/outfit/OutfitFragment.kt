package com.aboda.learning.outfiter.ui.outfit

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.aboda.learning.outfiter.data.local.ClothesData
import com.aboda.learning.outfiter.data.local.SharedPreferenceService
import com.aboda.learning.outfiter.data.model.Clothes
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.data.model.Outfit
import com.aboda.learning.outfiter.data.model.WeatherState
import com.aboda.learning.outfiter.databinding.FragmentOutfitBinding
import com.aboda.learning.outfiter.ui.main.BaseFragment
import com.aboda.learning.outfiter.ui.utils.EIGHT_HOURS
import com.aboda.learning.outfiter.ui.utils.TODAY
import com.aboda.learning.outfiter.ui.utils.makeGone
import com.aboda.learning.outfiter.ui.utils.makeVisible

class OutfitFragment() : BaseFragment<FragmentOutfitBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentOutfitBinding =
        FragmentOutfitBinding::inflate

    private lateinit var sharedPreference: SharedPreferences
    private lateinit var sharedPreferenceService: SharedPreferenceService

    private val clothesData = ClothesData()

    private lateinit var today: DailyWeather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeSharedPreferences()
        today = arguments?.getParcelable(TODAY)!!
        initializeScreen()
    }

    private fun initializeSharedPreferences() {
        sharedPreference = requireActivity().getSharedPreferences(
            "myOutfitSharedPreference", Context.MODE_PRIVATE
        )
        sharedPreferenceService = SharedPreferenceService(sharedPreference)
    }

    private fun initializeScreen() {
        val time = sharedPreferenceService.getTime()
        if (System.currentTimeMillis() - time > EIGHT_HOURS) {
            binding.apply {
                buttonPickMyOutfit.makeVisible()
                textViewHint.makeVisible()
                cardViewPants.makeGone()
                cardViewShirt.makeGone()
            }
            addCallback()
        } else {
            binding.apply {
                buttonPickMyOutfit.makeGone()
                textViewHint.makeGone()
                cardViewPants.makeVisible()
                cardViewShirt.makeVisible()
            }
            reloadOldOutfit()
        }
    }

    private fun reloadOldOutfit() {
        val oldShirtName = sharedPreferenceService.getShirtName()
        val oldPantsName = sharedPreferenceService.getPantsName()
        val oldShirt = clothesData.allTShirts.filter { it.name == oldShirtName }[0]
        val oldPants = clothesData.allPants.filter { it.name == oldPantsName }[0]
        val oldOutfit = Outfit(oldShirt, oldPants)
        loadOutfit(oldOutfit)
    }

    private fun loadOutfit(oldOutfit: Outfit) {
        loadPants(oldOutfit.pants)
        loadShirt(oldOutfit.tShirt)
    }

    private fun loadPants(pants: Clothes) {
        binding.apply {
            imageViewPants.setImageResource(pants.clothes)
            textViewPants.text = pants.name
        }
    }

    private fun loadShirt(tShirt: Clothes) {
        binding.apply {
            imageViewShirts.setImageResource(tShirt.clothes)
            textViewShirts.text = tShirt.name
        }
    }

    private fun addCallback() {
        binding.apply {
            buttonPickMyOutfit.setOnClickListener {
                buttonPickMyOutfit.makeGone()
                textViewHint.makeGone()
                cardViewPants.makeVisible()
                cardViewShirt.makeVisible()
                recordNewTime()
                loadOutfit(pickMyOutfit())
            }
        }
    }

    private fun recordNewTime() {
        sharedPreferenceService.setTime(System.currentTimeMillis())
    }

    private fun pickMyOutfit(): Outfit {
        val tShirt = when (today.weatherState) {
            WeatherState.COLD, WeatherState.WINDY -> getTShirtByState(WeatherState.COLD, WeatherState.WINDY)
            WeatherState.PARTLY_CLOUDY, WeatherState.CLOUDY -> getTShirtByState(WeatherState.MOSTLY_CLOUDY, WeatherState.CLOUDY)
            WeatherState.MOSTLY_CLOUDY, WeatherState.SUNNY -> getTShirtByState(WeatherState.PARTLY_CLOUDY, WeatherState.SUNNY)
            else -> getTShirtByState(WeatherState.PARTLY_CLOUDY, WeatherState.SUNNY)
        }
        val pants = getPants()
        return Outfit(tShirt, pants)
    }

    private fun getPants(): Clothes {
        var pants = clothesData.allPants.shuffled()
        val oldPantsName = sharedPreferenceService.getPantsName()
        while (pants[0].name == oldPantsName) {
            pants = pants.shuffled()
        }
        sharedPreferenceService.setPantsName(pants[0].name)
        return pants[0]
    }

    private fun getTShirtByState(firstState: WeatherState, lastState: WeatherState): Clothes {
        var shirts = clothesData.allTShirts.filter { it.state ==  firstState || it.state == lastState }
        val oldShirtName = sharedPreferenceService.getShirtName()
        while (shirts[0].name == oldShirtName) {
            shirts = shirts.shuffled()
        }
        sharedPreferenceService.setShirtName(shirts[0].name)
        return shirts[0]
    }
}