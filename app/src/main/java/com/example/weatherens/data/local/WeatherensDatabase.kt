package com.example.weatherens.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WeatherEntity::class), version = 1, exportSchema = false)
abstract class WeatherensDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}