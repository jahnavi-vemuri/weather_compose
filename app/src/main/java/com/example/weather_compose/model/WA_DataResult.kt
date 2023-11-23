package com.example.weather_compose.model

sealed class WA_DataResult {
    data class Success(
        val city: String,
        val country: String,
        val temp: Number,
        val description: String,
        val latitude: Double,
        val longitude: Double,
        val windSpeed: Double,
        val pressure: Int,
        val wid: Int?,
        val humidity: Int?,
        val weatherData: WeatherResponse
    ) : WA_DataResult()
    data class Error(val message: String): WA_DataResult()
}



