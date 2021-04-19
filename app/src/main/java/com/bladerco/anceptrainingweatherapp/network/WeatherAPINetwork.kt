package com.bladerco.anceptrainingweatherapp.network

import com.bladerco.anceptrainingweatherapp.model.WeatherResponseData
import com.bladerco.anceptrainingweatherapp.util.Constants.Companion.APP_ID
import com.bladerco.anceptrainingweatherapp.util.Constants.Companion.CITY
import com.bladerco.anceptrainingweatherapp.util.Constants.Companion.PATH
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPINetwork {

    @GET(PATH)
    fun getWeatherDataAsync(@Query(CITY) city: String, @Query(APP_ID) apiKey: String) : Deferred<WeatherResponseData>
}