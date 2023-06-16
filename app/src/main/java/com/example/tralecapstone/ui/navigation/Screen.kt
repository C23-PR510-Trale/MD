package com.example.tralecapstone.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import com.example.tralecapstone.ui.screen.DetailPlanScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")
    object Login : Screen("login")
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
    object HometoDetailPlan : Screen("home/{budget}/{preference}/{numrows}/{idPlan}/{title}/{price}/{rating}/{location}") {
        fun createRoute(budget:Int, preference:String, numrows:Int, idPlan: Int, title: String, price: Float, rating: Float, location: String)
                = "home/$budget/$preference/$numrows/$idPlan/$title/$price/$rating/$location"
    }
    object ListTriptoDetailPlan : Screen("{budget}/{preference}/{numrows}/{idPlan}/{title}/{price}/{rating}/{location}") {
        fun createRoute(budget:Int, preference:String, numrows:Int, idPlan: Int, title: String, price: Float, rating: Float, location: String)
                = "$budget/$preference/$numrows/$idPlan/$title/$price/$rating/$location"
    }
    object TripRectoDetailPlan : Screen("triprecommendation/{budget}/{preference}/{numrows}/{idPlan}/{title}/{price}/{rating}/{location}") {
        fun createRoute(budget:Int, preference:String, numrows:Int, idPlan: Int, title: String, price: Float, rating: Float, location: String)
                = "triprecommendation/$budget/$preference/$numrows/$idPlan/$title/$price/$rating/$location"
    }
    object TripRectoListTrip : Screen("triprecommendation/{category}") {
        fun createRoute(category:String) = "triprecommendation/$category"
    }
    object AddPlantoListTrip : Screen("addplan/{budget}/{preference}/{numrows}") {
        fun createRoute(budget:Int, preference:String, numrows:Int ) = "addplan/$budget/$preference/$numrows"
    }
    object DetailtoPayment : Screen("{budget}/{preference}/{numrows}/{idPlan}/{title}/{price}/{rating}/{location}/payment") {
        fun createRoute(budget:Int, preference:String, numrows:Int, idPlan: Int, title: String, price: Float, rating: Float, location: String)
                = "$budget/$preference/$numrows/$idPlan/$title/$price/$rating/$location/payment"
    }
    object AuthtoHome : Screen("auth/home") {
        fun createRoute() = "auth/home"
    }
}