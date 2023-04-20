package com.aboda.learning.outfiter.ui.tendays

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.databinding.FragmentTenDaysBinding
import com.aboda.learning.outfiter.ui.main.BaseFragment
import com.aboda.learning.outfiter.utils.ALL_DAILY_WEATHER


class TenDaysFragment() : BaseFragment<FragmentTenDaysBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentTenDaysBinding =
        FragmentTenDaysBinding::inflate

    private lateinit var allDailyWeather: List<DailyWeather>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allDailyWeather = arguments?.getParcelableArray(ALL_DAILY_WEATHER)!!.toList() as List<DailyWeather>
        loadDataToRecyclerView()
    }

    private fun loadDataToRecyclerView() {
        val dailyWeatherAdapter = DailyWeatherAdapter()
        dailyWeatherAdapter.submitList(allDailyWeather)
        binding.recyclerViewTenDays.adapter = dailyWeatherAdapter
    }

}