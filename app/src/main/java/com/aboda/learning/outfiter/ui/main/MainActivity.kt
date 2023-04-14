package com.aboda.learning.outfiter.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        fetchData(EgyptianCity.CAIRO)
        binding.searchView.editText.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchResult = getList(s.toString())
                val searchCityAdapter = SearchCityAdapter(object : OnCityClickListener {
                    override fun onCityClick(city: EgyptianCity) {
                        binding.searchBar.hint = city.cityName
                        binding.searchView.hide()
                        fetchData(city)
                    }
                })
                searchCityAdapter.submitList(searchResult)
                binding.suggestionRecyclerView.adapter = searchCityAdapter
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun getList(text: String): List<EgyptianCity> {
        return EgyptianCity.values().filter { it.cityName.contains(text) }
    }

    private fun fetchData(city: EgyptianCity) {
        val api = ApiService(this, city)
        api.fetchData()
    }

    override fun getAllDailyWeather(allDailyWeather: List<DailyWeather>) {
        runOnUiThread {
            val fragmentsAdapter = FragmentsAdapter(
                this,
                listOf(
                    TodayFragment(allDailyWeather[0]),
                    OutfitFragment(allDailyWeather[0]),
                    TenDaysFragment(allDailyWeather)
                )
            )
            binding.fragmentContainer.adapter = fragmentsAdapter
            TabLayoutMediator(binding.tabLayout, binding.fragmentContainer) { tab, position ->
                when (position) {
                    0 -> tab.text = "Today"
                    1 -> tab.text = "Outfit"
                    2 -> tab.text = "10 Days"
                }
            }.attach()
        }
    }
}