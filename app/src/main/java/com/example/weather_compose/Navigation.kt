package com.example.weather_compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather_compose.services.WA_Screen
import com.example.weather_compose.view.DetailScreen
import com.example.weather_compose.view.MainScreen
import com.example.weather_compose.viewModel.WeatherNetwork

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WA_Screen.MainWAScreen.route) {
        composable(route = WA_Screen.MainWAScreen.route) {
            MainScreen(navController = navController, viewModel = WeatherNetwork())
        }
        composable(
            route = WA_Screen.DetailWAScreen.route + "/{cityName}/{latitude}/{longitude}/{windSpeed}/{pressure}/{imageResId}/{description}/{temperature}/{humidity}",
            arguments = listOf(
                navArgument("cityName") { type = NavType.StringType },
                navArgument("latitude") { type = NavType.FloatType },
                navArgument("longitude") { type = NavType.FloatType },
                navArgument("windSpeed") { type = NavType.FloatType },
                navArgument("pressure") { type = NavType.IntType },
                navArgument("imageResId") { type = NavType.IntType },
                navArgument("temperature") { type = NavType.FloatType },
                navArgument("description"){type = NavType.StringType},
                navArgument("humidity") { type = NavType.IntType}
            )
        ) { entry ->
            DetailScreen(
                cityName = entry.arguments?.getString("cityName"),
                latitude = entry.arguments?.getFloat("latitude"),
                longitude = entry.arguments?.getFloat("longitude"),
                windSpeed = entry.arguments?.getFloat("windSpeed"),
                pressure = entry.arguments?.getInt("pressure"),
                imageResId = entry.arguments?.getInt("imageResId"),
                description = entry.arguments?.getString("description"),
                temperature = entry.arguments?.getFloat("temperature"),
                humidity = entry.arguments?.getInt("humidity")
            )
        }

    }
}






