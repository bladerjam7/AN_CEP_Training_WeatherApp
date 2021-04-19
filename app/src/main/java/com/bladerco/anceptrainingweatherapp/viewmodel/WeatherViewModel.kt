package com.bladerco.anceptrainingweatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bladerco.anceptrainingweatherapp.model.WeatherResponseData
import com.bladerco.anceptrainingweatherapp.network.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object WeatherViewModel: ViewModel() {

    val weatherLiveData: MutableLiveData<WeatherResponseData> = MutableLiveData()

    private val weatherRepository = WeatherRepository()

    private var weatherJob: Job? = null

    suspend fun getWeatherData(city: String, appId: String){

        weatherJob = viewModelScope.launch(Dispatchers.IO) {

            try {
                val weatherResponse = weatherRepository.getWeatherData(city, appId).await()

                weatherResponse?.let {
                    weatherLiveData.postValue(it)
                }
            } catch (e: Exception) {
                Log.d("TAG_ERROR", "getWeatherData: ${e.localizedMessage}")
            }
        }
    }
}