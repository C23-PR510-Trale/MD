package com.example.tralecapstone.ui.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.theme.Orange400
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import kotlinx.coroutines.delay
import java.util.*

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ConfirmationPaymentScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navController: NavController
) {
    // AnimationEffect

    val scale = remember {
        Animatable(0f)
    }

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

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = stringResource(id = R.string.click_back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "Payment Confirmation",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 10.dp, vertical = 16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .align(Alignment.Center),

            ) {

            GlideImage(
                model = R.drawable.loading,
                contentDescription = "loading confirmation page",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            )

            Text(
                text = "Wait a Minute!\n" +
                        "We will validate your payment",
                overflow = TextOverflow.Ellipsis,
//                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    color = Orange400,
//                ),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Your payment will be validated within 1 x 24 hours",
                color = Orange400,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(30.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationPaymentScreenPreview() {
    TraleCapstoneTheme {
//        ConfirmationPaymentScreen(navigateBack = {})
    }
}
