package com.example.weather_compose.helpers

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_compose.config.WA_Config

@Composable
fun BottomSheetContent(
    description: String?,
    temperature: Float?,
    humidity: Int?,
    windSpeed: Float?,
    pressure: Int?
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = WA_Config.bottomValue,
            style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = description ?: "",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val formattedTemp = "%.1f".format(temperature?.minus(WA_Config.temp))
            WeatherInfoItem(
                icon = Icons.Default.CloudQueue,
                title = WA_Config.temperature,
                value = "${formattedTemp}°C",
            )
            WeatherInfoItem(
                icon = Icons.Filled.WaterDrop,
                title = WA_Config.humidity,
                value = "${humidity}%"
            )
            WeatherInfoItem(
                icon = Icons.Default.Air,
                title = WA_Config.windSpeed,
                value = "${windSpeed}m/s"
            )
            WeatherInfoItem(
                icon = Icons.Default.Compress,
                title = WA_Config.pressure,
                value = "${pressure}hPa"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
