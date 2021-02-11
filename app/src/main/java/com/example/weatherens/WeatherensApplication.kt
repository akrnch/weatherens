package com.example.weatherens

import android.app.Application
import android.graphics.Bitmap
import androidx.databinding.library.BuildConfig
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class WeatherensApplication: Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        val options: DisplayImageOptions = DisplayImageOptions.Builder()
            .displayer(CircleBitmapDisplayer())
            .showImageOnLoading(android.R.color.transparent)
            .cacheInMemory(true).cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565).build()
        val config: ImageLoaderConfiguration = ImageLoaderConfiguration.Builder(this).defaultDisplayImageOptions(options).build()
        ImageLoader.getInstance().init(config);
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    
}