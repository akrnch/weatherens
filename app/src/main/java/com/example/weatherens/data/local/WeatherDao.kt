package com.example.weatherens.data.local

import androidx.room.*

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather")
    suspend fun getWeather(): List<WeatherEntity>?

    @Query("SELECT * FROM weather WHERE weatherId = :id_")
    suspend fun getWeatherById(id_: Int): WeatherEntity?

    @Query("SELECT * FROM weather WHERE lat = :lat_ AND lon = :lon_")
    suspend fun getWeatherByCoordinates(lat_: Float, lon_: Float): WeatherEntity?

    @Query("DELETE FROM weather")
    suspend fun flush()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForPlace(weather: WeatherEntity)

    @Update
    suspend fun updateWeatherForPlace(weather: WeatherEntity)

    @Delete
    suspend fun deleteWeatherForPlace(weather: WeatherEntity)

}