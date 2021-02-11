package com.example.weatherens.ui.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherens.R
import com.example.weatherens.data.remote.model.main.ApiSearchCity
import com.example.weatherens.databinding.ItemCityBinding

class SearchCityAdapter(viewModel: LocationSearchViewModel) : RecyclerView.Adapter<SearchCityAdapter.CityViewHolder>() {

    val vm: LocationSearchViewModel = viewModel
    private val cities: MutableList<ApiSearchCity> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemCityBinding>(inflater, R.layout.item_city, parent, false)
        return CityViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return@setOnClickListener
                val clicked = cities[position]
                vm.addWeatherForCity(clicked)
            }
        }
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.binding.apply {
            item = cities[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = cities.size

    fun setData(citiesList: List<ApiSearchCity>?) {
        citiesList?.let {
            cities.clear()
            cities.addAll(it)
            notifyDataSetChanged()
        }
    }

    class CityViewHolder(val binding: ItemCityBinding): RecyclerView.ViewHolder(binding.root)

//    inner class CityViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: ApiSearchCity) {
//            binding.item = item
//            binding.executePendingBindings()
//        }
//    }


}