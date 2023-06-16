package com.example.tralecapstone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.tralecapstone.ui.components.Login
import com.example.tralecapstone.ui.components.Register

typealias ComposableFun = @Composable ()->Unit

sealed class TabItem(val title:String, val screens:ComposableFun) {
    object Login : TabItem(title = "Login", screens = { Login() })
    object Register: TabItem(title = "Register", screens={ Register()},)
}