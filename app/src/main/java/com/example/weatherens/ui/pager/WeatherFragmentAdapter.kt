package com.example.weatherens.ui.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherens.data.local.WeatherEntity
import com.example.weatherens.ui.weather.WeatherFragment

class WeatherFragmentAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val items: MutableList<WeatherEntity> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment =
        WeatherFragment.newInstance(items[position])

    fun setData(newData: List<WeatherEntity>?) {
        newData?.let {
            items.clear()
            items.addAll(it)
            notifyDataSetChanged()
        }
    }
}