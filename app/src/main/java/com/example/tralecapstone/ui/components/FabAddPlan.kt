package com.example.tralecapstone.ui.components

import android.util.Log
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.screen.AddPlanScreen
import com.example.tralecapstone.ui.theme.Yellow

@Composable
fun floatingActionButtons(
    navController: NavController
) {

    val ctx = LocalContext.current

    // on below line we are creating a simple floating action button
    FloatingActionButton(
        onClick = {
            // on below line we are adding on click for our fab
            navController.navigate(Screen.AddPlan.route)
        },
        // adding background color for our button
        backgroundColor = Yellow,
        // adding color for our content of fab.
        contentColor = Color.White
    ) {
        // adding icon for button.
        Icon(Icons.Filled.Add, "")
    }
}
