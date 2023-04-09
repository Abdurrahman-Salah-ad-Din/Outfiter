package com.aboda.learning.outfiter.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentsAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentList: List<Fragment>
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = fragmentList.size

    override fun createFragment(position: Int) = fragmentList[position]

    fun getPageTitle(position: Int): String {
        return when(position) {
            0 -> "Today"
            1 -> "Outfit"
            else -> "10 Days"
        }
    }
}