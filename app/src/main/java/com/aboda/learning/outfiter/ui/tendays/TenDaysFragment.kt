package com.aboda.learning.outfiter.ui.tendays

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.databinding.FragmentTenDaysBinding

class TenDaysFragment(private val allDailyWeather: List<DailyWeather>) : Fragment() {

    private lateinit var binding: FragmentTenDaysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTenDaysBinding.inflate(layoutInflater, container, false)
        loadDataToRecyclerView()
        return binding.root
    }

    private fun loadDataToRecyclerView() {
        val dailyWeatherAdapter = DailyWeatherAdapter()
        dailyWeatherAdapter.submitList(allDailyWeather)
        binding.recyclerViewTenDays.adapter = dailyWeatherAdapter
    }

}