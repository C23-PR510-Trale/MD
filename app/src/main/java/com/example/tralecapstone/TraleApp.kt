package com.example.tralecapstone

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tralecapstone.ui.BottomBar
import com.example.tralecapstone.ui.components.Login
import com.example.tralecapstone.ui.components.Register
import com.example.tralecapstone.ui.components.floatingActionButtons
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.screen.*
import com.example.tralecapstone.ui.screen.sidefiture.VoluntripScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TraleApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        floatingActionButton = {
            if (
                currentRoute == Screen.Home.route
            ) {
                floatingActionButtons(
                    navController = navController
                )
            }
        },
        bottomBar = {
            if (
                currentRoute == Screen.Home.route ||
                currentRoute == Screen.Voluntrip.route ||
                currentRoute == Screen.Community.route ||
                currentRoute == Screen.Profile.route
            ) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController = navController)
            }
//            composable(Screen.Register.route) {
//                Register(
//                    onCLick = {
//                        navController.navigate(Screen.Login.route)
//                    }
//                )
//            }
//            composable(Screen.Login.route) {
//                Login(
//                    onCLick = {
//                        navController.navigate(Screen.Home.route)
//                    }
//                )
//            }
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { budget, preference, numrows, id, title, price, rating, location ->
                        navController.navigate(
                            Screen.HometoDetailPlan.createRoute(
                                budget,
                                preference,
                                numrows,
                                id,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                    navController = navController
                )
            }
            composable(Screen.TripRecommendation.route) {
                TripRecommendationScreen(
                    navigateToDetail = { budget, preference, numrows, id, title, price, rating, location ->
                        navController.navigate(
                            Screen.TripRectoDetailPlan.createRoute(
                                budget,
                                preference,
                                numrows,
                                id,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToListTrip = { category ->
                        navController.navigate(Screen.TripRectoListTrip.createRoute(category))
                    }
                )
            }
            composable(Screen.AddPlan.route) {
                AddPlanScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToListTrip = { budget, preference, numrows ->
                        navController.navigate(
                            Screen.AddPlantoListTrip.createRoute(
                                budget,
                                preference,
                                numrows
                            )
                        )
                    }
//                    { category ->
//                        navController.navigate(Screen.AddPlantoListTrip.createRoute(category))
//                    }
                )
            }
//            composable(Screen.ListTrip.route) {
//                ListTripScreen(
//                    navigateBack = {
//                        navController.navigateUp()
//                    },
//                    navigateToDetail = { id ->
//                        navController.navigate(Screen.ListTriptoDetailPlan.createRoute(id))
//                    },
//                    title = "Trip List"
//                )
//            }
            composable(Screen.Voluntrip.route) {
                VoluntripScreen()
//                val context = LocalContext.current
//                CartScreen(
//                    onOrderButtonClicked = { message ->
//                        shareOrder(context, message)
//                    }
//                )
            }
            composable(Screen.Auth.route) {
                AuthScreen(
                    navigateBack = {
                        navController.navigateUp()
                    })
            }
            composable(Screen.Login.route) {
                Login(/*navigateBack = navController.navigateUp()*/)
            }
            composable(Screen.Profile.route) {
                EditProfileScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(Screen.PaymentConfirmation.route) {
                ConfirmationPaymentScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navController = navController
                )
            }
            composable(Screen.PaymentDetails.route) {
                PaymentDetailScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToConfPayment = {
                        navController.navigate(Screen.PaymentConfirmation.route)
                    }
                )
            }
            composable(
                route = Screen.HometoDetailPlan.route,
                arguments = listOf(
                    navArgument("budget") { type = NavType.IntType },
                    navArgument("preference") { type = NavType.StringType },
                    navArgument("numrows") { type = NavType.IntType },
                    navArgument("idPlan") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("price") { type = NavType.FloatType },
                    navArgument("rating") { type = NavType.FloatType },
                    navArgument("location") { type = NavType.StringType },
                ),
            ) {
                val budget = it.arguments!!.getInt("budget")
                val preference = it.arguments!!.getString("preference")
                val numrows = it.arguments!!.getInt("numrows")
                val idPlan = it.arguments!!.getInt("idPlan")
                val title = it.arguments!!.getString("title")
                val price = it.arguments!!.getFloat("price").toDouble()
                val rating = it.arguments!!.getFloat("rating").toDouble()
                val location = it.arguments!!.getString("location")
                val context = LocalContext.current

                DetailPlanScreen(
                    budget = budget,
                    preference = preference!!,
                    numrows = numrows,
                    idPlan = idPlan,
                    title = title!!,
                    price = price,
                    rating = rating,
                    location = location!!,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToBooking = { budget, preference, numrows, id, title, price, rating, location ->
                        navController.navigate(
                            Screen.DetailtoPayment.createRoute(
                                budget,
                                preference,
                                numrows,
                                id,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                    onMessageClicked = { message ->
                        messageHost(context = context, message)
                    }
                )
            }
            composable(
                route = Screen.AddPlantoListTrip.route,
                arguments = listOf(
                    navArgument("budget") { type = NavType.IntType },
                    navArgument("preference") { type = NavType.StringType },
                    navArgument("numrows") { type = NavType.IntType }
                ),
            ) {
                val budget = it.arguments!!.getInt("budget")
                val preference = it.arguments!!.getString("preference")
                val numrows = it.arguments!!.getInt("numrows")
                val context = LocalContext.current

                ListTripScreen(
                    title = "Your Preference Trip List",
                    budget = budget,
                    preference = preference!!,
                    numrows = numrows,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToDetail = { budget, preference, numrows, id, title, price, rating, location ->
                        Log.d(
                            "cek navdetail",
                            "b:$budget p:$preference n:$numrows i:$id t:$title p:$price r:$rating l:$location"
                        )
                        navController.navigate(
                            Screen.ListTriptoDetailPlan.createRoute(
                                budget,
                                preference,
                                numrows,
                                id,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                )
            }
            composable(
                route = Screen.ListTriptoDetailPlan.route,
                arguments = listOf(
                    navArgument("budget") { type = NavType.IntType },
                    navArgument("preference") { type = NavType.StringType },
                    navArgument("numrows") { type = NavType.IntType },
                    navArgument("idPlan") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("price") { type = NavType.FloatType },
                    navArgument("rating") { type = NavType.FloatType },
                    navArgument("location") { type = NavType.StringType },
                ),
            ) {
                val budget = it.arguments!!.getInt("budget")
                val preference = it.arguments!!.getString("preference")
                val numrows = it.arguments!!.getInt("numrows")
                val idPlan = it.arguments!!.getInt("idPlan")
                val title = it.arguments!!.getString("title")
                val price = it.arguments!!.getFloat("price").toDouble()
                val rating = it.arguments!!.getFloat("rating").toDouble()
                val location = it.arguments!!.getString("location")
                val context = LocalContext.current

                DetailPlanScreen(
                    budget = budget,
                    preference = preference!!,
                    numrows = numrows,
                    idPlan = idPlan,
                    title = title!!,
                    price = price,
                    rating = rating,
                    location = location!!,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToBooking = { budget, preference, numrows, idPlan, title, price, rating, location ->
                        navController.navigate(
                            Screen.DetailtoPayment.createRoute(
                                budget,
                                preference,
                                numrows,
                                idPlan,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                    onMessageClicked = { message ->
                        messageHost(context = context, message)
                    }
                )
            }
            composable(
                route = Screen.TripRectoDetailPlan.route,
                arguments = listOf(
                    navArgument("budget") { type = NavType.IntType },
                    navArgument("preference") { type = NavType.StringType },
                    navArgument("numrows") { type = NavType.IntType },
                    navArgument("idPlan") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("price") { type = NavType.FloatType },
                    navArgument("rating") { type = NavType.FloatType },
                    navArgument("location") { type = NavType.StringType },
                ),
            ) {
                val budget = it.arguments!!.getInt("budget")
                val preference = it.arguments!!.getString("preference")
                val numrows = it.arguments!!.getInt("numrows")
                val idPlan = it.arguments!!.getInt("idPlan")
                val title = it.arguments!!.getString("title")
                val price = it.arguments!!.getFloat("price").toDouble()
                val rating = it.arguments!!.getFloat("rating").toDouble()
                val location = it.arguments!!.getString("location")
                val context = LocalContext.current

                DetailPlanScreen(
                    budget = budget,
                    preference = preference!!,
                    numrows = numrows,
                    idPlan = idPlan,
                    title = title!!,
                    price = price,
                    rating = rating,
                    location = location!!,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToBooking = { budget, preference, numrows, id, title, price, rating, location ->
                        navController.navigate(
                            Screen.DetailtoPayment.createRoute(
                                budget,
                                preference,
                                numrows,
                                id,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                    onMessageClicked = { message ->
                        messageHost(context = context, message)
                    }
                )
            }
            composable(
                route = Screen.TripRectoListTrip.route,
                arguments = listOf(navArgument("category") { type = NavType.StringType }),
            ) {
                val cat = it.arguments!!.getString("category")
                val context = LocalContext.current

                ListTripScreen(
                    title = cat!!,
                    budget = 300000,
                    numrows = 30,
                    preference = cat,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToDetail = { budget, preference, numrows, id, title, price, rating, location ->
                        navController.navigate(
                            Screen.ListTriptoDetailPlan.createRoute(
                                budget,
                                preference,
                                numrows,
                                id,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                )
            }
            composable(
                route = Screen.DetailtoPayment.route,
                arguments = listOf(
                    navArgument("budget") { type = NavType.IntType },
                    navArgument("preference") { type = NavType.StringType },
                    navArgument("numrows") { type = NavType.IntType },
                    navArgument("idPlan") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("price") { type = NavType.FloatType },
                    navArgument("rating") { type = NavType.FloatType },
                    navArgument("location") { type = NavType.StringType },
                ),
            ) {
                val budget = it.arguments!!.getInt("budget")
                val preference = it.arguments!!.getString("preference")
                val numrows = it.arguments!!.getInt("numrows")
                val idPlan = it.arguments!!.getInt("idPlan")
                val title = it.arguments!!.getString("title")
                val price = it.arguments!!.getFloat("price").toDouble()
                val rating = it.arguments!!.getFloat("rating").toDouble()
                val location = it.arguments!!.getString("location")
                val context = LocalContext.current

                PaymentScreen(
                    idPlan = idPlan,
                    title = title!!,
                    price = price,
                    rating = rating,
                    category = preference!!,
                    budget = budget,
                    numrows = numrows,
                    location = location!!,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToPaymentDetails = {
                        navController.navigate(Screen.PaymentDetails.route)
                    }
                )
            }
            composable(
                route = Screen.AuthtoHome.route,
            ) {
                HomeScreen(
                    navigateToDetail = { budget, preference, numrows, id, title, price, rating, location ->
                        navController.navigate(
                            Screen.ListTriptoDetailPlan.createRoute(
                                budget,
                                preference,
                                numrows,
                                id,
                                title,
                                price,
                                rating,
                                location
                            )
                        )
                    },
                    navController = navController
                )
            }
        }
    }
}

private fun messageHost(context: Context, text: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Host's Trip")
        putExtra(Intent.EXTRA_TEXT, text)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            "Host's Trip"
        )
    )
}
