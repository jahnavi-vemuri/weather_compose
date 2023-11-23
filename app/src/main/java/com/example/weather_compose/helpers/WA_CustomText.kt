package com.example.weather_compose.helpers

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weather_compose.config.WA_Config

@Composable
fun WeatherInfoItem(
    icon: ImageVector,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = title,
            style = TextStyle(fontSize = 14.sp),
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = value,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            color = Color.Black,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun WeatherInfoCard(
    city: String,
    country: String,
    temperature: Float,
    description: String,
    imageResId: Int
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val formattedTemperature = "%.1f".format(temperature - WA_Config.temp)
        val painter: Painter = painterResource(id = imageResId)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.height(200.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            text = city,
            style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
        )
        Text(
            text = country,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${formattedTemperature}°C",
            style = TextStyle(fontSize = 60.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = description,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
            modifier = Modifier.padding(8.dp)
        )
    }
}

