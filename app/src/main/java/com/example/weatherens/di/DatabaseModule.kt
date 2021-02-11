package com.example.weatherens.di

import android.app.Application
import androidx.room.Room
import com.example.weatherens.WeatherensConstants
import com.example.weatherens.data.local.WeatherDao
import com.example.weatherens.data.local.WeatherensDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): WeatherensDatabase {
        return Room.databaseBuilder(application, WeatherensDatabase::class.java, WeatherensConstants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: WeatherensDatabase): WeatherDao {
        return appDatabase.weatherDao()
    }
}