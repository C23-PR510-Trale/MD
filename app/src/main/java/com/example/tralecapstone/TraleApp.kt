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
import com.example.tralecapstone.ui.components.floatingActionButtons
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.screen.*
import com.example.tralecapstone.ui.screen.sidefiture.CommunityScreen
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
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.HometoDetailPlan.createRoute(id))
                    },
                    navController = navController
                )
            }
            composable(Screen.TripRecommendation.route) {
                TripRecommendationScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.TripRectoDetailPlan.createRoute(id))
                    },
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToListTrip ={category ->
                        navController.navigate(Screen.TripRectoListTrip.createRoute(category))
                    }
                )
            }
            composable(Screen.AddPlan.route) {
                AddPlanScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToListTrip ={
                        navController.navigate(Screen.ListTrip.route)
                    }
//                    { category ->
//                        navController.navigate(Screen.AddPlantoListTrip.createRoute(category))
//                    }
                )
            }
            composable(Screen.ListTrip.route) {
                ListTripScreen(
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToDetail = { id ->
                        navController.navigate(Screen.ListTriptoDetailPlan.createRoute(id))
                    },
                    title = "Trip List"
                )
            }
            composable(Screen.Voluntrip.route) {
                VoluntripScreen()
//                val context = LocalContext.current
//                CartScreen(
//                    onOrderButtonClicked = { message ->
//                        shareOrder(context, message)
//                    }
//                )
            }
            composable(Screen.Community.route) {
                CommunityScreen()
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
                arguments = listOf(navArgument("idPlan") { type = NavType.IntType }),
            ) {
                val id = it.arguments!!.getInt("idPlan")
                val context = LocalContext.current

                DetailPlanScreen(
                    idPlan = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToBooking = {id->
//                        navController.popBackStack()
                        navController.navigate(Screen.DetailtoPayment.createRoute(id))
//                        {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
                    },
                    onMessageClicked = { message ->
                        messageHost(context = context, message)
                    }
                )
            }
            composable(
                route = Screen.ListTriptoDetailPlan.route,
                arguments = listOf(navArgument("idPlan") { type = NavType.IntType }),
            ) {
                val id = it.arguments!!.getInt("idPlan")
                val context = LocalContext.current

                DetailPlanScreen(
                    idPlan = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToBooking = {id ->
                        navController.navigate(Screen.DetailtoPayment.createRoute(id))
                    },
                    onMessageClicked = { message ->
                        messageHost(context = context, message)
                    }
                )
            }
            composable(
                route = Screen.TripRectoDetailPlan.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                val id = it.arguments!!.getInt("id")
                val context = LocalContext.current

                DetailPlanScreen(
                    idPlan = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToBooking = {id ->
                        navController.navigate(Screen.DetailtoPayment.createRoute(id))
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
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToDetail = { id ->
                        navController.navigate(Screen.ListTriptoDetailPlan.createRoute(id))
                    },
                )
            }
            composable(
                route = Screen.DetailtoPayment.route,
                arguments = listOf(navArgument("idPlan") { type = NavType.IntType }),
            ) {
                val id = it.arguments!!.getInt("idPlan")
                val context = LocalContext.current

                PaymentScreen(
                    idPlan = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navController = navController,
                    navigateToDetail = {id ->
                        navController.navigate(Screen.PaymenttoDetailPlan.createRoute(id))
                    },
                    navigateToPaymentDetails = {
                        navController.navigate(Screen.PaymentDetails.route)
                    }
                )
            }
            composable(
                route = Screen.PaymenttoDetailPlan.route,
                arguments = listOf(navArgument("idPlan") { type = NavType.IntType }),
            ) {
                val id = it.arguments!!.getInt("idPlan")
                val context = LocalContext.current

                DetailPlanScreen(
                    idPlan = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToBooking = {
                        navController.popBackStack()
                        navController.navigate(Screen.DetailtoPayment.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onMessageClicked = { message ->
                        messageHost(context = context, message)
                    }
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
