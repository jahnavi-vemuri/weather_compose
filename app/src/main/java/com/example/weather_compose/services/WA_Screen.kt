package com.example.weather_compose.services

sealed class WA_Screen(val route: String) {
    object MainWAScreen : WA_Screen("main_screen")
    object DetailWAScreen : WA_Screen("detail_screen")

    fun withArgs(
        cityName: String,
        latitude: Float,
        longitude: Float,
        windSpeed: Float,
        pressure: Number,
        imageResId: Int,
        description: String,
        temperature: Number,
        humidity: Number
    ): String {
        return "detail_screen/$cityName/$latitude/$longitude/$windSpeed/$pressure/$imageResId/$description/$temperature/$humidity"
    }
}
