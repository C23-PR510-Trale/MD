package com.example.tralecapstone.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object History : Screen("history")
    object Buddy : Screen("buddy")
    object Profile : Screen("profile")
    object DetailPlan : Screen("home/{idPlan}") {
        fun createRoute(idPlan: Int) = "home/$idPlan"
    }
}