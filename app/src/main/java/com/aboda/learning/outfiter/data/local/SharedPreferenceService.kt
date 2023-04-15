package com.aboda.learning.outfiter.data.local

import android.content.SharedPreferences

class SharedPreferenceService(private val sharedPreference: SharedPreferences) {

    fun getTime() = sharedPreference.getLong("TIME", 0)

    fun setTime(time: Long) = sharedPreference.edit().putLong("TIME", time).apply()

    fun getShirtName() = sharedPreference.getString("SHIRT_NAME", "No name defined")

    fun setShirtName(shirtName: String) = sharedPreference.edit().putString("SHIRT_NAME", shirtName)
        .apply()

    fun getPantsName() = sharedPreference.getString("PANTS_NAME", "No name defined")

    fun setPantsName(pantsName: String) = sharedPreference.edit().putString("PANTS_NAME", pantsName)
        .apply()

}