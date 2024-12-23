package ir.developer.goalorpooch_compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.developer.goalorpooch_compose.ui.theme.screen.HomeScreen

@Composable
fun navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home-screen", builder = {
        composable("home-screen") {
            HomeScreen()
        }
    })
}