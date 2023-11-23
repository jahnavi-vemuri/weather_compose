package com.example.weather_compose.services
import android.graphics.Bitmap
import android.graphics.BitmapFactory.decodeResource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weather_compose.helpers.BottomSheetContent
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    markerLatLng: LatLng?,
    cityName: String?,
    weatherConditionId: Int?,
    windSpeed: Float?,
    pressure: Int?,
    description: String?,
    temperature: Float?,
    humidity: Int?,
    weatherImages: WA_Images
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(
                description = description,
                temperature = temperature,
                humidity = humidity,
                windSpeed = windSpeed,
                pressure = pressure
            )
        },
        sheetPeekHeight = 20.dp
    )
    {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val locationState = rememberMarkerState(
                position = markerLatLng ?: LatLng(0.0, 0.0)
            )
            val mapProperties by remember {
                mutableStateOf(MapProperties(mapType = MapType.NORMAL))
            }
            var selectedMarker by remember { mutableStateOf<Marker?>(null) }

            GoogleMap(
                modifier = modifier,
                cameraPositionState = cameraPositionState,
                properties = mapProperties
            ) {
                val imageResId = weatherImages.getWeatherConditionImageId(weatherConditionId)
                val b: Bitmap = decodeResource(weatherImages.getResources(), imageResId)
                val smallMarker: Bitmap = Bitmap.createScaledBitmap(b, 150, 150, false)
                val smallMarkerIcon: BitmapDescriptor =
                    BitmapDescriptorFactory.fromBitmap(smallMarker)
                Marker(
                    state = locationState,
                    draggable = true,
                    onClick = {
                        selectedMarker = it
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                        return@Marker false
                    },
                    title = cityName ?: "",
                    icon = smallMarkerIcon
                )
            }
        }
    }
}

