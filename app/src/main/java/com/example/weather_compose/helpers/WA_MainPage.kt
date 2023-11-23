package com.example.weather_compose.helpers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_compose.R
import com.example.weather_compose.config.WA_Config
import com.example.weather_compose.model.WA_DataResult
import com.example.weather_compose.services.WA_Images

@Composable
fun WeatherInfo(cityWeatherData: WA_DataResult?) {
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        text = stringResource(id = R.string.weather),
        style = TextStyle(fontSize = 35.sp, fontWeight = FontWeight.Bold),
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = stringResource(id = R.string.app_tag),
        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    )
    Spacer(modifier = Modifier.height(20.dp))
    if (cityWeatherData == null) {
        val defaultImageId = R.drawable.rainy
        WeatherInfoCard(
            city = stringResource(id = R.string.defaultCity),
            country = stringResource(id = R.string.defaultCountry),
            temperature = WA_Config.defaultTemp,
            description = stringResource(id = R.string.defaultDesc),
            imageResId = defaultImageId
        )
    } else {
        cityWeatherData.let { weatherDataResult ->
            when (weatherDataResult) {
                is WA_DataResult.Success -> {
                    val context = LocalContext.current
                    val resources = context.resources
                    val weatherImages = WA_Images(resources)
                    val imageResId =
                        weatherImages.getWeatherConditionImageId(weatherDataResult.wid)
                    WeatherInfoCard(
                        city = weatherDataResult.city,
                        country = weatherDataResult.country,
                        temperature = weatherDataResult.temp.toFloat(),
                        description = weatherDataResult.description,
                        imageResId = imageResId
                    )
                }
                is WA_DataResult.Error -> {
                    Text(
                        text = stringResource(id = R.string.error, weatherDataResult.message),
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                        color = Color.Red,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}
