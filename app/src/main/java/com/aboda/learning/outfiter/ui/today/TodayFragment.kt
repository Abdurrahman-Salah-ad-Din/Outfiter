package com.aboda.learning.outfiter.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.aboda.learning.outfiter.R
import com.aboda.learning.outfiter.data.model.DailyWeather
import com.aboda.learning.outfiter.data.model.HourlyWeather
import com.aboda.learning.outfiter.databinding.FragmentTodayBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class TodayFragment(private val today: DailyWeather) : Fragment() {

    private lateinit var binding: FragmentTodayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        dataSet.setDrawCircles(true)
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
        binding.lineChart.axisLeft.isEnabled = false
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.xAxis.isEnabled = false
        binding.lineChart.description.isEnabled = false
        binding.lineChart.legend.isEnabled = false
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
        binding.apply {
            textViewDateTime.text = formatDateAndTime(Date(), "MMMM d, h:mm a")
            textViewMaxMin.text = "Day ${today.maxTemperature}°↑. Night ${today.minTemperature}°↓"
            val now = getNow(today)
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