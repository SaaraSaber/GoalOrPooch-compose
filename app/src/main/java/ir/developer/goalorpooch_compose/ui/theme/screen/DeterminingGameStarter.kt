package ir.developer.goalorpooch_compose.ui.theme.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DeterminingGameStarter(navController: NavController) {
    Scaffold ( modifier = Modifier.fillMaxSize()){innerPadding ->

    }
}


@Preview
@Composable
private fun DeterminingGameStarterPreview() {
    val navController = rememberNavController()
    DeterminingGameStarter(navController)
}