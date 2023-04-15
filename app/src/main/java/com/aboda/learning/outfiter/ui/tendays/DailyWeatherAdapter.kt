package com.aboda.learning.outfiter.ui.tendays

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.data.model.HourlyWeather
import com.aboda.learning.outfiter.ui.utils.makeGone
import com.aboda.learning.outfiter.ui.utils.makeVisible
import com.aboda.learning.outfiter.databinding.ItemDailyWeatherBinding
import com.aboda.learning.outfiter.ui.utils.formatTime

class DailyWeatherAdapter : ListAdapter<DailyWeather, DailyWeatherAdapter.DailyWeatherViewHolder>(
    DailyWeatherItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherViewHolder {
        val binding =
            ItemDailyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyWeatherViewHolder(binding)
    }

    inner class DailyWeatherViewHolder(
        private val binding: ItemDailyWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dailyWeather: DailyWeather) {
            val hourlyAdapter = HourlyWeatherAdapter()
            hourlyAdapter.submitList(dailyWeather.dayHours)
            binding.apply {
                val context = binding.root.context
                textViewWeatherItemMaxTemperature.text = context.getString(
                    R.string.formatted_temperature,
                    dailyWeather.maxTemperature.toInt()
                )
                textViewWeatherItemMinTemperature.text = context.getString(
                    R.string.formatted_temperature,
                    dailyWeather.minTemperature.toInt()
                )
                textViewHumidity.text = context.getString(
                    R.string.formatted_percentage,
                    getHumidity(dailyWeather.dayHours)
                )
                textViewSunsetSunrise.text = context.getString(
                    R.string.sunrise_sunset_text,
                    formatTime(dailyWeather.sunRise, "yyyy-MM-dd'T'HH:mm", "h:mm a"),
                    formatTime(dailyWeather.sunSet, "yyyy-MM-dd'T'HH:mm", "h:mm a")
                )
                textViewWind.text = context.getString(
                    R.string.speed_text,
                    dailyWeather.windSpeed.toString()
                )
                textViewWeatherItemDate.text = formatTime(
                    dailyWeather.dayDate, "yyyy-MM-dd",
                    "EEEE, MMM d"
                )
                textViewChanceOfRain.text = context.getString(
                    R.string.formatted_percentage,
                    dailyWeather.precipitationProbability
                )
                imageViewWeatherItemState.setImageResource(dailyWeather.dailyWeatherState)
                textViewUvIndex.text = dailyWeather.UVIndex.toString()
                textViewWeatherItemState.text = dailyWeather.stringState
                recyclerViewHourlyWeather.adapter = hourlyAdapter
                binding.weatherDataContainer.setOnClickListener {
                    if (expendedDataContainer.visibility == View.VISIBLE)
                        expendedDataContainer.makeGone()
                    else
                        expendedDataContainer.makeVisible()
                }
            }
        }

        private fun getHumidity(dayHours: List<HourlyWeather>) = (dayHours.sumOf { it.humidity } /
                dayHours.size)

    }

    override fun onBindViewHolder(holder: DailyWeatherViewHolder, position: Int) {
        val dailyWeather = getItem(position)
        holder.bind(dailyWeather)
    }

}

class DailyWeatherItemDiffCallback : DiffUtil.ItemCallback<DailyWeather>() {
    override fun areItemsTheSame(oldItem: DailyWeather, newItem: DailyWeather): Boolean {
        return oldItem.dayDate == newItem.dayDate
    }

    override fun areContentsTheSame(oldItem: DailyWeather, newItem: DailyWeather): Boolean {
        return oldItem == newItem
    }
}