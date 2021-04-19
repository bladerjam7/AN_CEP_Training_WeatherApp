package com.bladerco.anceptrainingweatherapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bladerco.anceptrainingweatherapp.R
import com.bladerco.anceptrainingweatherapp.util.Constants
import com.bladerco.anceptrainingweatherapp.util.Constants.Companion.API_KEY
import com.bladerco.anceptrainingweatherapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_look_up.setOnClickListener {
            getWeather()
            val cityIntent = Intent(this, WeatherListActivity::class.java)
            cityIntent.putExtra("city_name_main", et_city_name.text.toString().capitalize(Locale.getDefault()))
            startActivity(cityIntent)
        }
    }

    private fun getWeather() {
        val cityName = et_city_name.text.toString()
        job = lifecycleScope.launch(Dispatchers.Main){
            WeatherViewModel.getWeatherData(cityName, API_KEY)
        }

    }
}