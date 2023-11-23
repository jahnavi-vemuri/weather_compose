package com.example.weather_compose.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weather_compose.R
import com.example.weather_compose.model.WA_DataResult
import com.example.weather_compose.helpers.Loader
import com.example.weather_compose.helpers.WeatherInfo
import com.example.weather_compose.services.WA_Screen
import com.example.weather_compose.services.SearchTextField
import com.example.weather_compose.viewModel.WeatherNetwork


@Composable
fun MainScreen(navController: NavController, viewModel: WeatherNetwork) {
    var cityName by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var cityWeatherData by remember { mutableStateOf<WA_DataResult?>(null) }
    var loading by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            searchText = cityName,
            onSearch = { query ->
                cityName = query
                cityWeatherData = null
                loading = true
                keyboardController?.hide()
                try {
                    viewModel.fetchWeather(cityName) { result ->
                        when (result) {
                            is WA_DataResult.Success -> {
                                val weatherData = result.weatherData
                                cityName = weatherData.name
                                cityWeatherData = result
                            }
                            is WA_DataResult.Error -> {
                                cityWeatherData = result
                            }
                        }
                        loading = false
                    }
                } catch (e: Exception) {
                    loading = false
                }
            }
        )

        Loader(loading)

        if (!loading) {
            WeatherInfo(cityWeatherData)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (cityName.isNotEmpty()) {
                    val weatherData = cityWeatherData as? WA_DataResult.Success
                    val latitude = weatherData?.latitude?.toFloat() ?: 0.0f
                    val longitude = weatherData?.longitude?.toFloat() ?: 0.0f
                    val windSpeed = weatherData?.windSpeed?.toFloat() ?: 0.0f
                    val pressure = weatherData?.pressure?.toInt() ?: 0.0f
                    val imageResId = weatherData?.wid ?: 0
                    val description = weatherData?.description ?: ""
                    val temperature = weatherData?.temp ?:0.0f
                    val humidity = weatherData?.humidity ?:0

                    navController.navigate(
                        WA_Screen.DetailWAScreen.withArgs(
                            cityName,
                            latitude,
                            longitude,
                            windSpeed,
                            pressure,
                            imageResId,
                            description,
                            temperature,
                            humidity
                        )
                    )
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(stringResource(id = R.string.button))
        }

    }
}