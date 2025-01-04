package ir.developer.goalorpooch_compose

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ir.developer.goalorpooch_compose.Utils.HOME_SCREEN
import ir.developer.goalorpooch_compose.Utils.SETTING_SCREEN
import ir.developer.goalorpooch_compose.Utils.STARTER_SCREEN
import ir.developer.goalorpooch_compose.ui.screen.DeterminingGameStarter
import ir.developer.goalorpooch_compose.ui.screen.HomeScreen
import ir.developer.goalorpooch_compose.ui.screen.SelectCardScreen
import ir.developer.goalorpooch_compose.ui.screen.SettingScreen
import ir.developer.goalorpooch_compose.ui.screen.ShowCardsScreen

@Composable
fun Navigation(sharedViewModel: SharedViewModel) {
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
            HomeScreen(navController,sharedViewModel)
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
        composable(route = Utils.SELECT_CARD_SCREEN + "/{idItem}",
            arguments = listOf(
                navArgument("idItem") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            SelectCardScreen(
                idItemSelected = entry.arguments?.getInt("idItem")!!,
                navController = navController,
//                sharedViewModel = sharedViewModel
            )
        }

        composable(route = Utils.SHOW_SELECTED_CARD_SCREEN) {
            ShowCardsScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    })
}