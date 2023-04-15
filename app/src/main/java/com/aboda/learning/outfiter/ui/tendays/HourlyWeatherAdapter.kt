package com.aboda.learning.outfiter.ui.tendays

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.HourlyWeather
import com.aboda.learning.outfiter.databinding.ItemHourlyWeatherBinding
import com.aboda.learning.outfiter.ui.utils.formatTime

class HourlyWeatherAdapter() :
    ListAdapter<HourlyWeather, HourlyWeatherAdapter.HourlyWeatherViewHolder>(HourlyWeatherItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyWeatherViewHolder {
        val binding =
            ItemHourlyWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HourlyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HourlyWeatherViewHolder, position: Int) {
        val hourlyWeather = getItem(position)
        holder.bind(hourlyWeather)
    }

    inner class HourlyWeatherViewHolder(
        private val binding: ItemHourlyWeatherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(hourlyWeather: HourlyWeather) {
            binding.textViewHourlyWeatherTime.text = formatTime(hourlyWeather.time,
                "yyyy-MM-dd'T'HH:mm", "h a")
            binding.textViewHourlyWeatherTemperature.text = binding.root.context.getString(R.string
                .formatted_temperature, hourlyWeather.temperature.toInt())
            binding.imageViewHourlyWeatherState.setImageResource(hourlyWeather.HourlyWeatherState)
        }
    }
}

class HourlyWeatherItemDiffCallback : DiffUtil.ItemCallback<HourlyWeather>() {
    override fun areItemsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: HourlyWeather, newItem: HourlyWeather): Boolean {
        return oldItem == newItem
    }
}