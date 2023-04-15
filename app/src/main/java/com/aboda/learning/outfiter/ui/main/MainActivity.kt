package com.aboda.learning.outfiter.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.data.model.EgyptianCity
import com.aboda.learning.outfiter.data.network.ApiService
import com.aboda.learning.outfiter.data.network.NetworkService
import com.aboda.learning.outfiter.databinding.ActivityMainBinding
import com.aboda.learning.outfiter.ui.outfit.OutfitFragment
import com.aboda.learning.outfiter.ui.tendays.TenDaysFragment
import com.aboda.learning.outfiter.ui.today.TodayFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NetworkService {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallback()
        fetchWeatherDataForCity(EgyptianCity.CAIRO)
    }

    private fun addCallback() {
        binding.searchView.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(
                newText: CharSequence?, start: Int, before: Int, count: Int
            ) {
                val searchResult = getSearchResult(newText.toString())
                val searchCityAdapter = SearchCityAdapter(object : OnCityClickListener {
                    override fun onCityClick(city: EgyptianCity) {
                        binding.searchBar.hint = city.cityName
                        binding.searchView.hide()
                        fetchWeatherDataForCity(city)
                    }
                })
                searchCityAdapter.submitList(searchResult)
                binding.suggestionRecyclerView.adapter = searchCityAdapter
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun getSearchResult(text: String): List<EgyptianCity> {
        return EgyptianCity.values().filter { it.cityName.contains(text) }
    }

    private fun fetchWeatherDataForCity(city: EgyptianCity) {
        val api = ApiService(this, city)
        api.fetchData()
    }

    override fun getAllDailyWeather(allDailyWeather: List<DailyWeather>) {
        runOnUiThread {
            val weatherToday = allDailyWeather[0]

            val todayFragment = TodayFragment()
            todayFragment.createBundleWithParcelable(weatherToday)

            val outfitFragment = OutfitFragment()
            outfitFragment.createBundleWithParcelable(weatherToday)

            val tenDaysFragment = TenDaysFragment()
            tenDaysFragment.createBundleWithParcelableArray(allDailyWeather.toTypedArray())

            val fragmentsAdapter = FragmentsAdapter(
                this, listOf(
                    todayFragment, outfitFragment, tenDaysFragment
                )
            )
            binding.fragmentContainer.adapter = fragmentsAdapter
            TabLayoutMediator(binding.tabLayout, binding.fragmentContainer) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.today)
                    1 -> tab.text = getString(R.string.outfit)
                    2 -> tab.text = getString(R.string._10_days)
                }
            }.attach()
        }
    }
}