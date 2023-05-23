package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tralecapstone.R
import com.example.tralecapstone.TraleApp
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box{
        Image(
            painter = painterResource(id = R.drawable.background),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Image(
            painter = painterResource(id = R.drawable.logo_app),
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
        SplashScreen()
    }
}