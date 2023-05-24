package com.example.tralecapstone

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tralecapstone.ui.BottomBar
import com.example.tralecapstone.ui.components.floatingActionButtons
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.screen.HomeScreen

@Composable
fun TraleApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        floatingActionButton = {
            floatingActionButtons()
        },
        bottomBar = {
//            if (currentRoute != Screen.DetailPlan.route) {
                BottomBar(navController)
//            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { id ->
                        navController.navigate(Screen.DetailPlan.createRoute(id))
                    }
                )
            }
//            composable(Screen.Cart.route) {
//                val context = LocalContext.current
//                CartScreen(
//                    onOrderButtonClicked = { message ->
//                        shareOrder(context, message)
//                    }
//                )
//            }
//            composable(Screen.Profile.route) {
//                ProfileScreen()
//            }
//            composable(
//                route = Screen.DetailReward.route,
//                arguments = listOf(navArgument("rewardId") { type = NavType.LongType }),
//            ) {
//                val id = it.arguments?.getLong("rewardId") ?: -1L
//                DetailScreen(
//                    rewardId = id,
//                    navigateBack = {
//                        navController.navigateUp()
//                    },
//                    navigateToCart = {
//                        navController.popBackStack()
//                        navController.navigate(Screen.Cart.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                )
//            }
        }
    }
}
