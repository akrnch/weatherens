package com.example.weatherens.data

import com.example.weatherens.data.local.WeatherDao
import com.example.weatherens.data.local.WeatherEntity
import com.example.weatherens.data.remote.ApiClient
import com.example.weatherens.data.remote.model.main.ApiSearchCity
import com.skydoves.sandwich.*
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER")
class WeatherRepository @Inject constructor(
    private val apiClient: ApiClient,
    private val weatherDao: WeatherDao
) : Repository {

    suspend fun addWeather(lat: Float, lon: Float, onSuccess: () -> Unit, onError: (String?) -> Unit) {
        val query = "%.4f".format(lat).replace(",",".") + "," + "%.4f".format(lon).replace(",",".")
        val existing = weatherDao.getWeatherByCoordinates(lat, lon)
        if (existing == null) {
            val weather = apiClient.getWeatherForLocation(query)
            weather.suspendOnSuccess {
                weatherDao.insertWeatherForPlace(data!!.toRoom())
                onSuccess()
            }.onError {
                onError("[Code: $statusCode]: ${message()}")
            }.onException {
                onError(message)
            }
        } else {
            onSuccess();
        }
    }

    suspend fun updateAllWeather(onSuccess: () -> Unit, onError: (String?) -> Unit) {
        val allWeather = weatherDao.getWeather()
        if (!allWeather.isNullOrEmpty()) {
            for (item: WeatherEntity in allWeather) {
                val query = "%.4f".format(item.lat) + "," + "%.4f".format(item.lon);
                val updated = withTimeout(1_000) {
                    apiClient.getWeatherForLocation(query)
                }
                updated.suspendOnSuccess {
                    weatherDao.updateWeatherForPlace(data!!.toRoom())
                    onSuccess()
                }.onError {
                    onError("[Code: $statusCode]: ${message()}")
                }.onException {
                    onError(message)
                }
            }
        } else {
            onSuccess()
        }
    }

    suspend fun getAllWeather(onSuccess: () -> Unit, onError: (String?) -> Unit): List<WeatherEntity>? {
        var result: List<WeatherEntity>? = null
        try {
            result = weatherDao.getWeather()
            onSuccess()
        } catch (throwable: Throwable) {
            onError(throwable.message)
        }
        return result
    }

    suspend fun removeWeather(weather: WeatherEntity, onSuccess: () -> Unit, onError: (String?) -> Unit) {
        try {
            weatherDao.deleteWeatherForPlace(weather)
            onSuccess();
        } catch (throwable: Throwable) {
            onError(throwable.message)
        }
    }

    suspend fun flushWeather(onSuccess: () -> Unit, onError: (String?) -> Unit) {
        try {
            weatherDao.flush()
            onSuccess();
        } catch (throwable: Throwable) {
            onError(throwable.message)
        }
    }

    suspend fun getLocationByInput(input: String, onSuccess: () -> Unit, onError: (String?) -> Unit): List<ApiSearchCity>? {
        var result: List<ApiSearchCity>? = null
        val response = apiClient.getLocationByInput(input = input)
        response.suspendOnSuccess {
            if (data != null) {
                result = data
                onSuccess()
            }
        }.onError {
            onError("[Code: $statusCode]: ${message()}")
        }.onException {
            onError(message)
        }
        return result
    }
}