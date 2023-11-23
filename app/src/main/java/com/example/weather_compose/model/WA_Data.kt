package com.example.weather_compose.model

data class WeatherResponse(
    val name: String,
    val main: WeatherData,
    val sys: WeatherSysData,
    val coord: WeatherCoordData,
    val wind: WeatherWindData,
    val weather: List<WeatherDescriptionData>
)

data class WeatherData(
    val temp: Float,
    val humidity: Int,
    val pressure: Int
)

data class WeatherSysData(
    val country: String
)

data class WeatherCoordData(
    val lat: Double,
    val lon: Double
)

data class WeatherWindData(
    val speed: Double
)

data class WeatherDescriptionData(
    val description: String,
    val id: Int
)






