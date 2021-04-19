package com.bladerco.anceptrainingweatherapp.model

data class WeatherResponseData(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Data>,
    val message: Int
)