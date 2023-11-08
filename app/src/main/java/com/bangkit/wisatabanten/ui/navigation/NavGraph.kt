package com.bangkit.wisatabanten.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bangkit.wisatabanten.ui.screen.detail.DetailScreen
import com.bangkit.wisatabanten.ui.screen.home.HomeScreen
import com.bangkit.wisatabanten.ui.screen.profile.ProfileScreen

@Composable
fun SetupNavGraph(
    modifier: Modifier,
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.Home.route, modifier) {
        composable(Screen.Home.route) {
            HomeScreen {
                navController.navigate(Screen.DetailTour.createRoute(it))
            }
        }
        composable(
            route = Screen.DetailTour.route,
            arguments = listOf(navArgument("tourId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("tourId") ?: "0"
            DetailScreen(
                tourId = id,
                onNavigateBack = {navController.navigateUp()}
            )
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen()
        }

    }
}