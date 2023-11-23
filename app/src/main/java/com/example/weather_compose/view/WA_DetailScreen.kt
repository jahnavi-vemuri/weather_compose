package com.example.weather_compose.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.weather_compose.services.GoogleMapView
import com.example.weather_compose.services.WA_Images
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    cityName: String?,
    latitude: Float?,
    longitude: Float?,
    windSpeed: Float?,
    pressure: Int?,
    imageResId: Int?,
    description: String?,
    temperature: Float?,
    humidity: Int?
) {
    val context = LocalContext.current
    val resources = context.resources

    val indiaState = LatLng(latitude?.toDouble() ?: 0.0, longitude?.toDouble() ?: 0.0)
    val defaultCameraPosition = CameraPosition.fromLatLngZoom(indiaState, 6f)

    val cameraPositionState = rememberCameraPositionState {
        position = defaultCameraPosition
    }
    GoogleMapView(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        markerLatLng = indiaState,
        cityName = cityName,
        weatherConditionId = imageResId,
        windSpeed = windSpeed,
        pressure = pressure,
        description = description,
        temperature = temperature,
        humidity = humidity,
        weatherImages = WA_Images(resources)
    )
}
