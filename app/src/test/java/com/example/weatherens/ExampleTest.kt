package com.example.weatherens

import android.content.Intent
import android.os.Build
import com.example.weatherens.data.WeatherRepository
import com.example.weatherens.data.local.WeatherEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject


@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
class ExampleTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var weatherRepository: WeatherRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun shouldReturnSingleEntity() {
        val myScope = GlobalScope
        var allWeather: List<WeatherEntity>? = null
        runBlocking {
            myScope.launch {
                weatherRepository.flushWeather(onSuccess = {}, onError = {})
                weatherRepository.addWeather(53.89F, 27.56F, onSuccess = {}, onError = {})
                allWeather = weatherRepository.getAllWeather(onSuccess = {}, onError = {})
                assertNotNull(allWeather!!.size == 1)
            }
        }
    }

}