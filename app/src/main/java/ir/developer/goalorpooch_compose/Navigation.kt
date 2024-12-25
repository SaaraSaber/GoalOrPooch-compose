package ir.developer.goalorpooch_compose

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.developer.goalorpooch_compose.Utils.HOME_SCREEN
import ir.developer.goalorpooch_compose.Utils.SETTING_SCREEN
import ir.developer.goalorpooch_compose.Utils.STARTER_SCREEN
import ir.developer.goalorpooch_compose.ui.theme.screen.DeterminingGameStarter
import ir.developer.goalorpooch_compose.ui.theme.screen.HomeScreen
import ir.developer.goalorpooch_compose.ui.theme.screen.SelectCardScreen
import ir.developer.goalorpooch_compose.ui.theme.screen.SettingScreen

@Composable
fun navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_SCREEN, builder = {
        composable(
            route = HOME_SCREEN,
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            }
        ) {
            HomeScreen(navController)
        }
        composable(
            route = SETTING_SCREEN,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            }
        )
        {
            SettingScreen(navController)
        }
        composable(
            route = STARTER_SCREEN,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            },
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            }
        )
        {
            DeterminingGameStarter(navController)
        }
        composable(Utils.SELECT_CARD_SCREEN) {
            SelectCardScreen(navController)
        }
    })
}