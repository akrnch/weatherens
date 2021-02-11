package com.example.weatherens.data.remote.model.main

import com.example.weatherens.data.local.WeatherEntity
import com.example.weatherens.data.remote.model.ApiLocation
import com.example.weatherens.data.remote.model.ApiWeather
import com.google.gson.annotations.SerializedName

data class ApiCurrentWeather (
    @SerializedName("location") val apiLocation : ApiLocation,
    @SerializedName("current") val current : ApiWeather
) {
    fun toRoom(): WeatherEntity {
        return WeatherEntity(null,
            apiLocation.name,
            current.lastUpdatedEpoch,
            apiLocation.lat,
            apiLocation.lon,
            current.isDay,
            current.tempC,
            current.tempF,
            current.condition.icon,
            current.condition.text,
            current.windKph,
            current.windMph,
            current.precipMm,
            current.precipIn,
            current.humidity)
    }
}
