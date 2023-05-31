package com.example.tralecapstone.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Community : Screen("community")
    object Message : Screen("message")
    object Profile : Screen("profile")
    object Payment : Screen("payment")
    object HometoDetailPlan : Screen("home/{idPlan}") {
        fun createRoute(idPlan: Int) = "home/$idPlan"
    }
    object HometoDetailtoPayment : Screen("home/{idPlan}/payment") {
        fun createRoute(idPlan: Int) = "home/$idPlan/payment"
    }
}