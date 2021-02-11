package com.example.weatherens.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiWeatherCondition (
    @SerializedName("text") val text : String,
    @SerializedName("icon") val icon : String,
    @SerializedName("code") val code : Int
)
