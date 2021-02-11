package com.example.weatherens.data.remote

import com.example.weatherens.data.remote.model.main.ApiCurrentWeather
import com.example.weatherens.data.remote.model.main.ApiSearchCity
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/current.json")
    suspend fun getWeatherForLocation(@Query("key") apiKey: String,
                                      @Query("q") location: String): ApiResponse<ApiCurrentWeather>

    @GET("v1/search.json")
    suspend fun getLocationByInput(@Query("key") apiKey: String,
                                   @Query("q") location: String): ApiResponse<List<ApiSearchCity>>

}