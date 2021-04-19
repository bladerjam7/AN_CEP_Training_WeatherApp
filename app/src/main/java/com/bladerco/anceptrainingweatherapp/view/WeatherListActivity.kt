package com.bladerco.anceptrainingweatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import com.bladerco.anceptrainingweatherapp.R
import com.bladerco.anceptrainingweatherapp.view.adapter.WeatherAdapter
import com.bladerco.anceptrainingweatherapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather_list.*

class WeatherListActivity : AppCompatActivity() {

    private val weatherAdapter = WeatherAdapter(this, listOf(),this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)

        initialize()

        WeatherViewModel.weatherLiveData.observe(this, {
            weatherAdapter.updateList(it.list)
        })


    }

    private fun initialize() {
        val cityName = intent.getStringExtra("city_name_main")
        setSupportActionBar(tb_list)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = cityName
        }

        rv_weather_list.adapter = weatherAdapter
        weatherAdapter.updateCityName(cityName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}