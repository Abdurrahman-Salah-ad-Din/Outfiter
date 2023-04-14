package com.aboda.learning.outfiter.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aboda.learning.outfiter.data.model.EgyptianCity
import com.aboda.learning.outfiter.databinding.ItemCityBinding

class SearchCityAdapter(
    private val onCityClickListener: OnCityClickListener
) : ListAdapter<EgyptianCity, SearchCityAdapter.SearchCityViewHolder>(SearchCityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchCityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchCityViewHolder, position: Int) {
        val city = getItem(position)
        holder.bind(city, onCityClickListener)
    }

    inner class SearchCityViewHolder(
        private val binding: ItemCityBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: EgyptianCity, onCityClickListener: OnCityClickListener) {
            binding.searchItemTextview.text = city.cityName
            binding.searchItemTextview.setOnClickListener {
                onCityClickListener.onCityClick(city)
            }

        }
    }
}

class SearchCityDiffCallback : DiffUtil.ItemCallback<EgyptianCity>() {
    override fun areItemsTheSame(oldItem: EgyptianCity, newItem: EgyptianCity): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: EgyptianCity, newItem: EgyptianCity): Boolean {
        return oldItem == newItem
    }
}

interface OnCityClickListener {
    fun onCityClick(city: EgyptianCity)
}