package com.example.weatherens.di

import com.example.weatherens.data.WeatherRepository
import com.example.weatherens.data.local.WeatherDao
import com.example.weatherens.data.remote.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideWeatherRepository(
        apiClient: ApiClient,
        weatherDao: WeatherDao
    ): WeatherRepository {
        return WeatherRepository(apiClient, weatherDao)
    }
}