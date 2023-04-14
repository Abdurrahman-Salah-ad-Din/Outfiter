package com.aboda.learning.outfiter.data.network

import android.util.Log
import com.aboda.learning.outfiter.data.model.EgyptianCity
import com.aboda.learning.outfiter.data.model.parseDailyResponse
import okhttp3.*
import java.io.IOException

class ApiService(
    private val networkService: NetworkService,
    city: EgyptianCity
) : Callback {

    private val url = HttpUrl.Builder()
        .scheme(NetworkUtils.SCHEME)
        .host(NetworkUtils.HOST)
        .addPathSegment(NetworkUtils.FIRST_PATH)
        .addPathSegment(NetworkUtils.SECOND_PATH)
        .addQueryParameter("latitude", city.latitude.toString())
        .addQueryParameter("longitude", city.longitude.toString())
        .addQueryParameter(
            "hourly",
            "temperature_2m,relativehumidity_2m,cloudcover,precipitation_probability"
        )
        .addQueryParameter("start_date", getStartDate())
        .addQueryParameter("end_date", getDateAfterTenDays())
        .addQueryParameter("timezone", "Africa/Cairo")
        .addQueryParameter(
            "daily",
            "temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,precipitation_probability_max,windspeed_10m_max"
        )
        .build()

    companion object {
        private val client = OkHttpClient()
    }

    private val request = Request.Builder().url(url).get().build()

    fun fetchData() {
        client.newCall(request).enqueue(this)
    }

    override fun onFailure(call: Call, e: IOException) {
        Log.e("Error", e.message.toString())
    }

    override fun onResponse(call: Call, response: Response) {
        response.body?.string().let {
            networkService.getAllDailyWeather(parseDailyResponse(it))
        }
    }
}
