package com.aboda.learning.outfiter.ui.outfit

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aboda.learning.outfiter.data.model.Clothes
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.data.model.Outfit
import com.aboda.learning.outfiter.databinding.FragmentOutfitBinding
import com.aboda.learning.outfiter.ui.utils.getAllPants
import com.aboda.learning.outfiter.ui.utils.getAllTShirts
import com.aboda.learning.outfiter.ui.utils.makeGone
import com.aboda.learning.outfiter.ui.utils.makeVisible

class OutfitFragment(private val today: DailyWeather) : Fragment() {

    private lateinit var binding: FragmentOutfitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOutfitBinding.inflate(layoutInflater, container, false)
        initializeScreen()
        return binding.root
    }

    private fun initializeScreen() {
        val sharedPreference = requireActivity().getSharedPreferences(
            "myOutfitSharedPreference", Context.MODE_PRIVATE
        )
        val time = sharedPreference.getLong("TIME", 0)
        if (System.currentTimeMillis() - time > 1000 * 60 * 60 * 24) {
            binding.buttonPickMyOutfit.makeVisible()
            binding.textViewHint.makeVisible()
            binding.cardViewPants.makeGone()
            binding.cardViewShirt.makeGone()
            addCallback()
        } else {
            binding.buttonPickMyOutfit.makeGone()
            binding.textViewHint.makeGone()
            binding.cardViewPants.makeVisible()
            binding.cardViewShirt.makeVisible()
            reloadOldOutfit()
        }
    }

    private fun reloadOldOutfit() {
        val sharedPreference = requireActivity().getSharedPreferences(
            "myOutfitSharedPreference", Context.MODE_PRIVATE
        )
        val oldShirtName = sharedPreference.getString("SHIRT_NAME", "No name defined")
        val oldPantsName = sharedPreference.getString("PANTS_NAME", "No name defined")
        val oldShirt = getAllTShirts().filter { it.name == oldShirtName }[0]
        val oldPants = getAllPants().filter { it.name == oldPantsName }[0]
        val oldOutfit = Outfit(oldShirt, oldPants)
        loadOutfit(oldOutfit)
    }

    private fun loadOutfit(oldOutfit: Outfit) {
        loadPants(oldOutfit.pants)
        loadShirt(oldOutfit.tShirt)
    }

    private fun loadPants(pants: Clothes) {
        binding.imageViewPants.setImageResource(pants.clothes)
        binding.textViewPants.text = pants.name
    }

    private fun loadShirt(tShirt: Clothes) {
        binding.imageViewShirts.setImageResource(tShirt.clothes)
        binding.textViewShirts.text = tShirt.name
    }

    private fun addCallback() {
        binding.buttonPickMyOutfit.setOnClickListener {
            binding.buttonPickMyOutfit.makeGone()
            binding.textViewHint.makeGone()
            binding.cardViewPants.makeVisible()
            binding.cardViewShirt.makeVisible()
            recordNewTime()
            loadOutfit(pickMyOutfit())
        }
    }

    private fun recordNewTime() {
        val sharedPreference = requireActivity().getSharedPreferences(
            "myOutfitSharedPreference",
            Context.MODE_PRIVATE
        )
        val editor = sharedPreference.edit()
        editor.putLong("TIME", System.currentTimeMillis())
        editor.apply()
    }

    private fun pickMyOutfit(): Outfit {
        val tShirt = when (today.stringState) {
            "Cold", "Windy" -> getTShirtByState("Cold", "Windy")
            "Partly cloudy", "Cloudy" -> getTShirtByState("Mostly cloudy", "Cloudy")
            "Mostly cloudy", "Sunny" -> getTShirtByState("Partly cloudy", "Sunny")
            else -> getTShirtByState("Partly cloudy", "Sunny")
            // الميثود دي getHotTShirt() المفروض هنافي ال else
            // بس لسه مطلعتش الهدوم الصيفي :(
        }
        val pants = getPants()
        return Outfit(tShirt, pants)
    }

    private fun getPants(): Clothes {
        var pants = getAllPants().shuffled()
        val myOutfitSharedPreference = requireActivity().getSharedPreferences(
            "myOutfitSharedPreference",
            Context.MODE_PRIVATE
        )
        val oldPantsName = myOutfitSharedPreference.getString("PANTS_NAME", "No name defined")
        while (pants[0].name == oldPantsName) {
            pants = pants.shuffled()
        }
        myOutfitSharedPreference.edit().putString("PANTS_NAME", pants[0].name).apply()
        return pants[0]
    }

    private fun getTShirtByState(firstState: String, lastState: String): Clothes {
        var shirts = getAllTShirts().filter { it.state == firstState || it.state == lastState }
        val myOutfitSharedPreference = requireActivity().getSharedPreferences(
            "myOutfitSharedPreference",
            Context.MODE_PRIVATE
        )
        val oldShirtName = myOutfitSharedPreference.getString("SHIRT_NAME", "No name defined")
        while (shirts[0].name == oldShirtName) {
            shirts = shirts.shuffled()
        }
        myOutfitSharedPreference.edit().putString("SHIRT_NAME", shirts[0].name).apply()
        return shirts[0]
    }
}