package com.example.weather_compose.services

import android.content.res.Resources
import com.example.weather_compose.R

class WA_Images(private val resources: Resources) {
    fun getWeatherConditionImageId(id: Int?): Int {
        return when (id) {
            in 800..803 -> R.drawable.cloudy
            in 803..805 -> R.drawable.rainy
            in 600..699 -> R.drawable.snowy
            in 806..999 -> R.drawable.sunny
            else -> R.drawable.sunny
        }
    }
    fun getResources(): Resources {
        return resources
    }
}