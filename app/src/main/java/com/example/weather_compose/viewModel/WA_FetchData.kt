package com.example.weather_compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather_compose.config.WA_Config
import com.example.weather_compose.config.WA_Config.API_KEY
import com.example.weather_compose.model.WA_DataResult
import com.example.weather_compose.services.WA_Service
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherNetwork : ViewModel() {
    fun fetchWeather(cityName: String, callback: (WA_DataResult) -> Unit) {
        viewModelScope.launch {
            try {
                val weatherData = fetchDataFromAPI(cityName)
                when (weatherData) {
                    is WA_DataResult.Success -> {
                        callback(weatherData)
                    }
                    is WA_DataResult.Error -> {
                        callback(weatherData)
                    }
                }
            } catch (e: Exception) {
                callback(WA_DataResult.Error("API request failed: ${e.message}"))
            }
        }
    }

    private suspend fun fetchDataFromAPI(cityName: String): WA_DataResult {
        return try {
            val retrofit = createRetrofitInstance()
            val service = retrofit.create(WA_Service::class.java)
            val response = service.getWeatherData(cityName, API_KEY)

            if (response.isSuccessful) {
                val weatherData = response.body()
                if (weatherData != null) {
                    return WA_DataResult.Success(
                        weatherData.name,
                        weatherData.sys.country ?: "",
                        weatherData.main?.temp ?: 0,
                        weatherData.weather.getOrNull(0)?.description ?: "",
                        weatherData.coord?.lat ?: 0.0,
                        weatherData.coord?.lon ?: 0.0,
                        weatherData.wind?.speed ?: 0.0,
                        weatherData.main?.pressure ?: 0,
                        weatherData.weather.getOrNull(0)?.id,
                        weatherData.main?.humidity ?: 0,
                        weatherData
                    )
                } else {
                    return WA_DataResult.Error(WA_Config.city)
                }
            } else {
                return WA_DataResult.Error(WA_Config.failed)
            }
        } catch (e: Exception) {
            return WA_DataResult.Error("API request failed: ${e.message}")
        }
    }

    private fun createRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WA_Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}


