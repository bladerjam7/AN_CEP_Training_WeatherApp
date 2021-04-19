package com.bladerco.anceptrainingweatherapp.util

object Converters {

    fun celsiusToFahrenheit(tempC: Double): Double = (tempC /10f) * 1.8 + 32

    fun cloudsToCloudyCheckConvert(status: String): String {
        if(status == "Clouds")
            return "Cloudy"

        return status
    }
}