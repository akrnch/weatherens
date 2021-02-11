package com.example.weatherens.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.weatherens.R
import com.example.weatherens.data.local.WeatherEntity
import com.example.weatherens.databinding.FragmentWeatherBinding
import com.example.weatherens.ui.pager.PagingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val sharedViewModel: PagingViewModel by activityViewModels()

    companion object {
        private const val DATA = "weather_data"

        fun newInstance(weather: WeatherEntity): WeatherFragment {
            val args: Bundle = Bundle()
            args.putParcelable(DATA, weather)
            val fragment = WeatherFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentWeatherBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        val weather = arguments?.getParcelable<WeatherEntity>(DATA)
        binding.weather = weather
        binding.apply {
            vm = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        binding.handler = DeleteHandler()
        return binding.root
    }

    class DeleteHandler() {
        fun onWeatherDelete(viewModel: PagingViewModel, weather: WeatherEntity) {
            viewModel.delete(weather)
        }
    }

}