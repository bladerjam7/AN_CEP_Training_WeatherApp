package com.bladerco.anceptrainingweatherapp.network

import com.bladerco.anceptrainingweatherapp.model.Weather
import com.bladerco.anceptrainingweatherapp.model.WeatherResponseData
import com.bladerco.anceptrainingweatherapp.util.Constants.Companion.URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {

    private var weatherAPI: WeatherAPINetwork

    init {
        weatherAPI = createWeatherAPI(createRetrofit())
    }

    private fun createWeatherAPI(retrofitInstance: Retrofit): WeatherAPINetwork {
        return retrofitInstance.create(WeatherAPINetwork::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun getWeatherData(city: String, appId: String): Deferred<WeatherResponseData> = weatherAPI.getWeatherDataAsync(city, appId)

}