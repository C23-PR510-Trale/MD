package com.example.tralecapstone.ui.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.PersonPin
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tralecapstone.R
import com.example.tralecapstone.datastore.DataPreferences
import com.example.tralecapstone.datastore.PreferenceViewModel
import com.example.tralecapstone.datastore.ViewModelFactoryDataStore
import com.example.tralecapstone.ui.navigation.NavigationItem
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun BottomBar(navController: NavHostController) {

    BottomNavigation {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val pref = DataPreferences.getInstance(LocalContext.current.dataStore)
        val prefVM : PreferenceViewModel = viewModel(
            factory = ViewModelFactoryDataStore(pref)
        )

        val token by prefVM.getToken().observeAsState()
        val name by prefVM.getName().observeAsState()
        var loginState = false

        name?.let {
            Log.d("cek name result", it)
        }
        token?.let { result ->
            Log.d("cek token result", result)
            if(result.isNotBlank() && result.isNotEmpty()) loginState = true
        }

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Rounded.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_voluntrip),
                icon = Icons.Rounded.PersonPin,
                screen = Screen.Voluntrip
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = if(!loginState) Screen.Auth else Screen.Profile
            ),
        )

        BottomNavigation(backgroundColor = Color.White) {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(bottom = 4.dp)
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            color = DarkGray,
                            fontSize = 11.sp,
                            fontWeight = FontWeight(600)
                        ) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavPreview() {
    TraleCapstoneTheme {
        BottomBar(navController = rememberNavController())
    }
}
