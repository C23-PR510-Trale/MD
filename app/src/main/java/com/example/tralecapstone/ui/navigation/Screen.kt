package com.example.tralecapstone.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Community : Screen("community")
    object Voluntrip : Screen("voluntrip")
    object Profile : Screen("profile")
    object ListTrip : Screen("listtrip")
    object TripRecommendation : Screen("triprecommendation")
    object AddPlan : Screen("addplan")
    object Payment : Screen("payment")
    object PaymentDetails : Screen("paymentdetails")
    object PaymentConfirmation : Screen("paymentconfirmation")
    object HometoAddPlan : Screen("home/addplan") {
        fun createRoute(idPlan: Int) = "home/addplan"
    }
    object HometoTripRec : Screen("home/triprecommendation") {
        fun createRoute() = "home/triprecommendation"
    }
    object HometoDetailPlan : Screen("home/{idPlan}") {
        fun createRoute(idPlan: Int) = "home/$idPlan"
    }
    object PaymenttoDetailPlan : Screen("payment/{idPlan}") {
        fun createRoute(idPlan: Int) = "payment/$idPlan"
    }
    object ListTriptoDetailPlan : Screen("listtrip/{idPlan}") {
        fun createRoute(idPlan: Int) = "listtrip/$idPlan"
    }
    object TripRectoDetailPlan : Screen("triprecommendation/{idPlan}") {
        fun createRoute(idPlan: Int) = "triprecommendation/$idPlan"
    }
    object TripRectoListTrip : Screen("triprecommendation/{category}") {
        fun createRoute(category: String) = "triprecommendation/$category"
    }
    object AddPlantoListTrip : Screen("addplan/{category}") {
        fun createRoute(category: String) = "home/$category"
    }
    object DetailtoPayment : Screen("{idPlan}/payment") {
        fun createRoute(idPlan: Int) = "$idPlan/payment"
    }
    object HometoDetailtoPayment : Screen("home/{idPlan}/payment") {
        fun createRoute(idPlan: Int) = "home/$idPlan/payment"
    }
}