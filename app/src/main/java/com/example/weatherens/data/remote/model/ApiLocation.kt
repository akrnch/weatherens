package com.example.weatherens.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiLocation(
    @SerializedName("name") val name : String,
    @SerializedName("region") val region : String,
    @SerializedName("country") val country : String,
    @SerializedName("lat") val lat : Float,
    @SerializedName("lon") val lon : Float,
    @SerializedName("tz_id") val timezoneId : String,
    @SerializedName("localtime_epoch") val localtimeEpoch : Long,
    @SerializedName("localtime") val localtime : String
)
