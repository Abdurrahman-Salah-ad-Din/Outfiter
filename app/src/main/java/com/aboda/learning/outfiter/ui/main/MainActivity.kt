package com.aboda.learning.outfiter.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.aboda.learning.outfiter.databinding.ActivityMainBinding
import com.aboda.learning.outfiter.ui.outfit.OutfitFragment
import com.aboda.learning.outfiter.ui.tendays.TenDaysFragment
import com.aboda.learning.outfiter.ui.today.TodayFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentsAdapter = FragmentsAdapter(this,
            listOf(
                TodayFragment(),
                OutfitFragment(),
                TenDaysFragment()
            )
        )
        binding.fragmentContainer.adapter = fragmentsAdapter
        TabLayoutMediator(binding.tabLayout, binding.fragmentContainer) { tab, position ->
            tab.text = fragmentsAdapter.getPageTitle(position)
        }.attach()
    }
}