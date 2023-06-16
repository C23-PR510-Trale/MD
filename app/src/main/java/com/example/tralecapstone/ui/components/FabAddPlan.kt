package com.example.tralecapstone.ui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.theme.Yellow

@Composable
fun floatingActionButtons(
    navController: NavController
) {
    FloatingActionButton(
        onClick = {
            navController.navigate(Screen.AddPlan.route)
        },
        backgroundColor = Yellow,
        contentColor = Color.White
    ) {
        Icon(Icons.Filled.Add, "")
    }
}
