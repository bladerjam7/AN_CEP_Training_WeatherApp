package com.bladerco.anceptrainingweatherapp.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bladerco.anceptrainingweatherapp.R
import com.bladerco.anceptrainingweatherapp.databinding.ItemWeatherDataBinding
import com.bladerco.anceptrainingweatherapp.model.Data
import com.bladerco.anceptrainingweatherapp.util.Converters
import com.bladerco.anceptrainingweatherapp.view.WeatherDetailActivity
import kotlin.math.ceil

class WeatherAdapter(val activity: Activity, var weatherList: List<Data>, val context: Context): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    class WeatherViewHolder(val binding: ItemWeatherDataBinding): RecyclerView.ViewHolder(binding.root)
    private var cityName: String? = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherResponseData = weatherList[position]

        holder.binding.apply {
            tvWeatherStatus.text = Converters.cloudsToCloudyCheckConvert(weatherResponseData.weather[0].main)
            tvTempNumber.text = context.getString(R.string.weather_temp_F,
                    ceil(Converters.celsiusToFahrenheit(weatherResponseData.main.temp)))

            cvItem.setOnClickListener {
                val detailIntent = Intent(activity, WeatherDetailActivity::class.java)
                detailIntent.putExtra("detailPosition", position)
                detailIntent.putExtra("city_name", cityName)
                activity.startActivity(detailIntent)
            }
        }
    }

    override fun getItemCount(): Int = weatherList.size

    fun updateList(weatherList: List<Data>){

        this.weatherList = weatherList
        notifyDataSetChanged()
    }

    fun updateCityName(city: String?) {
        cityName = city
    }

}