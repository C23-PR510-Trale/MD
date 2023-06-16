package com.example.tralecapstone.ui.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tralecapstone.MainActivity
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // AnimationEffect
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(3000L)
        navController.navigate(Screen.Home.route)
    }

    Box {
        Image(
            painter = if (isSystemInDarkTheme()) {
                painterResource(id = R.drawable.background_dark)
            } else painterResource(id = R.drawable.background),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Image(
            painter = if (isSystemInDarkTheme()) {
                painterResource(id = R.drawable.logoapp_dark)
            } else painterResource(id = R.drawable.logo_app),
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(200.dp)
                .clip(RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    TraleCapstoneTheme {
        SplashScreen(navController = NavController(MainActivity()))
    }
}