package com.example.weatherens.data.remote

import com.example.weatherens.WeatherensConstants
import com.example.weatherens.data.remote.model.main.ApiCurrentWeather
import com.example.weatherens.data.remote.model.main.ApiSearchCity
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class ApiClient @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getWeatherForLocation(location: String): ApiResponse<ApiCurrentWeather> =
        apiService.getWeatherForLocation(WeatherensConstants.WEATHER_API_KEY, location)

    suspend fun getLocationByInput(input: String): ApiResponse<List<ApiSearchCity>> =
        apiService.getLocationByInput(WeatherensConstants.WEATHER_API_KEY, input)
}