package com.aboda.learning.outfiter.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.data.model.HourlyWeather
import com.aboda.learning.outfiter.databinding.FragmentTodayBinding
import com.aboda.learning.outfiter.ui.main.BaseFragment
import com.aboda.learning.outfiter.ui.utils.TODAY
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TodayFragment() : BaseFragment<FragmentTodayBinding>() {

    override val bindingInflater: (LayoutInflater) -> FragmentTodayBinding =
        FragmentTodayBinding::inflate

    private lateinit var today: DailyWeather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        today = arguments?.getParcelable(TODAY)!!
        loadTodayData()
    }

    private fun setUpLineChart(entries: List<Entry>) {
        setUpLineChartBackground()
        removeLineChartPadding()
        customizeLineOfLineChart(entries)
    }

    private fun customizeLineOfLineChart(entries: List<Entry>) {
        val dataSet = LineDataSet(entries, "")
        dataSet.color = requireContext().getColor(R.color.md_theme_onSurfaceVariant)
        dataSet.valueTextColor = requireContext().getColor(R.color.md_theme_onBackground)
        val lineData = LineData(dataSet)
        dataSet.setDrawFilled(true)
        dataSet.fillDrawable =
            ContextCompat.getDrawable(requireContext(), R.drawable.line_chart_background)
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.enableDashedLine(10f, 30f, 0f)
        binding.lineChart.data = lineData
    }

    private fun removeLineChartPadding() {
        binding.lineChart.setViewPortOffsets(0f, 0f, 0f, 0f)
        binding.lineChart.setExtraOffsets(0f, 0f, 0f, 0f)
    }

    private fun setUpLineChartBackground() {
        binding.apply {
            lineChart.axisLeft.isEnabled = false
            lineChart.axisRight.isEnabled = false
            lineChart.xAxis.isEnabled = false
            lineChart.description.isEnabled = false
            lineChart.legend.isEnabled = false
        }
    }

    private fun loadTodayData() {
        val data = mutableListOf<Entry>()
        data.add(Entry(0.0F, 0.0F))
        today.dayHours.mapIndexed { index, hourlyWeather ->
            val entry = Entry((index + 1).toFloat(), hourlyWeather.temperature.toFloat())
            data.add(entry)
        }
        setUpLineChart(data)
        setUpWeatherData(today)
    }

    private fun setUpWeatherData(today: DailyWeather) {
        val now = getNow(today)
        binding.apply {
            textViewDateTime.text = formatDateAndTime(Date(), "MMMM d, h:mm a")
            textViewMaxMin.text = requireContext().getString(
                R.string.max_min_day_night_temperature,
                today.maxTemperature.toInt(), today.minTemperature.toInt()
            )
            textViewTemperature.text = now.temperature.toString()
            textViewState.text = today.stringState
            imageViewState.setImageResource(now.HourlyWeatherState)
        }
    }

    private fun getNow(today: DailyWeather): HourlyWeather {
        val formattedDate = formatDateAndTime(Date(), "yyyy-MM-dd'T'HH:mm")
        val index = formattedDate.substring(11, 13).toInt()
        return today.dayHours[index]
    }

    private fun formatDateAndTime(date: Date, pattern: String): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(date).toString()
    }

}