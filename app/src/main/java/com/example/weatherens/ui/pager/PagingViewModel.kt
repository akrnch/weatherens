package com.example.weatherens.ui.pager

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.example.weatherens.data.WeatherRepository
import com.example.weatherens.data.local.WeatherEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    private val _weatherListLiveData: MutableLiveData<List<WeatherEntity>?> = MutableLiveData<List<WeatherEntity>?>()
    val weatherListLiveData: LiveData<List<WeatherEntity>?>
        get() = _weatherListLiveData
    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String>
        get() = _toastLiveData
    private val _deletedLiveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val deletedLiveData: LiveData<Boolean>
        get() = _deletedLiveData

    fun loadSavedWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.set(true)
            val weatherPromise: Deferred<List<WeatherEntity>?> = async {
                weatherRepository.getAllWeather(
                    onSuccess = {
                        isLoading.set(false)
                    },
                    onError = {
                        isLoading.set(false)
                        _toastLiveData.postValue(it)
                    }
                )
            }
            _weatherListLiveData.postValue(weatherPromise.await())
        }
    }

    fun delete(item: WeatherEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.set(true)
            weatherRepository.removeWeather(
                item,
                onSuccess = {
                    isLoading.set(false)
                    _deletedLiveData.postValue(true)
                },
                onError = {
                    isLoading.set(false)
                }
            )
        }
    }

}