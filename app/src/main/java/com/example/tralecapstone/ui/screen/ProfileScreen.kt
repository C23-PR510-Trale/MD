package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.DarkGrey
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow

@Composable
fun ProfileScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.background_profile),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = stringResource(id = R.string.click_back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
                    .align(Alignment.Start)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                modifier = modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .align(CenterHorizontally),
                contentDescription = null
            )

            Text(
                text = "Aghni Qisthina Al Rahma",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = DarkGrey,
                modifier = modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
            )

            RatingBar(
                rating = 3,
                modifier = Modifier.align(CenterHorizontally)
            )

            Text(
                "Aka Ilha di serial drama korea duty after school. Namun meninggal sangat mengenaskan :( ditangan young soo si psikopat gila csat",
                textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally),
            )

            Text(
                "Planned to visit",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )

            Text(
                "Connect with Him",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
            )

            Image(
                painter = painterResource(id = R.drawable.logo_ig),
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.logo_twitter),
                contentDescription = null,
                modifier = Modifier.size(34.dp)
            )
            Icon(
                imageVector = Icons.Rounded.PhoneAndroid,
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }
    }
}

@Composable
private fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
) {

    Row(modifier) {
        for (i in 1..rating) {
            Icon(
                imageVector = Icons.Rounded.Star,
                tint = Yellow,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        for (i in 1..(5-rating)) {
            Icon(
                imageVector = Icons.Rounded.StarOutline,
                tint = Yellow,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Spacer(
                modifier = Modifier
                    .size(10.dp)
                    .align(Alignment.CenterVertically)
            )
        }

        Icon(
            imageVector = Icons.Rounded.Reviews,
            contentDescription = null,
            tint = Color.LightGray,
            modifier = Modifier
                .size(28.dp)
                .padding(start = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    TraleCapstoneTheme() {
        ProfileScreen(navigateBack = { })
    }
}