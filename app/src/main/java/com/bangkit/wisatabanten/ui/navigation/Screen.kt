package com.bangkit.wisatabanten.ui.navigation

sealed class Screen(val route: String) {

    object Home : Screen("home")

    object Profile : Screen("profile")

    object DetailTour : Screen("detail/{tourId}") {
        fun createRoute(tourId: String) = "detail/$tourId"
    }
}