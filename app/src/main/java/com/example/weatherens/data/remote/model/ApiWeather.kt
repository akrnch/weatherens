package com.example.weatherens.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiWeather(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch : Long,
    @SerializedName("last_updated") val lastUpdated : String,
    @SerializedName("temp_c") val tempC : Float,
    @SerializedName("temp_f") val tempF : Float,
    @SerializedName("is_day") val isDay : Int,
    @SerializedName("condition") val condition : ApiWeatherCondition,
    @SerializedName("wind_mph") val windMph : Float,
    @SerializedName("wind_kph") val windKph : Float,
    @SerializedName("wind_degree") val windDegree : Int,
    @SerializedName("wind_dir") val windDir : String,
    @SerializedName("pressure_mb") val pressureMb : Float,
    @SerializedName("pressure_in") val pressureIn : Float,
    @SerializedName("precip_mm") val precipMm : Float,
    @SerializedName("precip_in") val precipIn : Float,
    @SerializedName("humidity") val humidity : Int,
    @SerializedName("cloud") val cloud : Int,
    @SerializedName("feelslike_c") val feelslikeC : Float,
    @SerializedName("feelslike_f") val feelslikeF : Float,
    @SerializedName("vis_km") val visKm : Float,
    @SerializedName("vis_miles") val visMiles : Float,
    @SerializedName("uv") val uv : Int,
    @SerializedName("gust_mph") val gustMph : Float,
    @SerializedName("gust_kph") val gustKph : Float
)
