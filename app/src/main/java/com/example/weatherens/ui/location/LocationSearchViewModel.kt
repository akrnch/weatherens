package com.example.weatherens.ui.location

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.example.weatherens.data.WeatherRepository
import com.example.weatherens.data.remote.model.main.ApiSearchCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationSearchViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var searchText: String = ""
    set(value) {
        field = value
        loadData()
    }

    val isLoading: ObservableBoolean = ObservableBoolean(false)
    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String>
        get() = _toastLiveData
    private val _citiesLiveData: MutableLiveData<List<ApiSearchCity>?> = MutableLiveData<List<ApiSearchCity>?>()
    val citiesLiveData: LiveData<List<ApiSearchCity>?>
        get() = _citiesLiveData
    private val _addCityObservable: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val addCityObservable: LiveData<Boolean>
        get() = _addCityObservable

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.set(true)
            val citiesPromise: Deferred<List<ApiSearchCity>?> = async {
                weatherRepository.getLocationByInput(searchText,
                    onSuccess = {
                        isLoading.set(false)
                    },
                    onError = {
                        isLoading.set(false)
                        _toastLiveData.postValue(it)
                    }
                )
            }
            _citiesLiveData.postValue(citiesPromise.await())
        }
    }

    fun addWeatherForCity(cityInfo: ApiSearchCity) {
        isLoading.set(true)
        viewModelScope.launch(Dispatchers.IO) {
            weatherRepository.addWeather(
                cityInfo.lat.toFloat(),
                cityInfo.lon.toFloat(),
                onSuccess = {
                    isLoading.set(false)
                    _addCityObservable.postValue(true)
                }
            ) {
                isLoading.set(false)
                _toastLiveData.postValue(it)
            }
        }
    }

}