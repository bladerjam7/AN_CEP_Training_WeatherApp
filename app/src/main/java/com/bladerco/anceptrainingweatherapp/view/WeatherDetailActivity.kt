package com.bladerco.anceptrainingweatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bladerco.anceptrainingweatherapp.R
import com.bladerco.anceptrainingweatherapp.util.Converters
import com.bladerco.anceptrainingweatherapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather_detail.*

class WeatherDetailActivity : AppCompatActivity() {

    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_detail)

        initialize()

        WeatherViewModel.weatherLiveData.observe(this, {
            it.list[position].let { data ->
                tv_temp_number_detail.text = getString(R.string.weather_temp_F, Converters.celsiusToFahrenheit(data.main.temp))
                tv_feels_like_number_detail.text = getString(R.string.weather_temp_F, Converters.celsiusToFahrenheit(data.main.feels_like))
                tv_weather_status_detail.text = Converters.cloudsToCloudyCheckConvert(data.weather[0].main)
                tv_weather_status_description_detail.text = data.weather[0].description
            }
        })
    }

    private fun initialize() {
        // Grab selected position
        position = intent.getIntExtra("detailPosition", 0)
        val cityName = intent.getStringExtra("city_name")

        setSupportActionBar(tb_details)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
            title = cityName
            setHomeButtonEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}