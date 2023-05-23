package com.example.tralecapstone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun TripCategories(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row {
        Column(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.cat_landmark),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(60.dp)
            )
            Text(
                text = stringResource(R.string.landmark),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
            )
        }
        Column(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.cat_culinary),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(R.string.culinary),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
            )
        }
        Column(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.cat_nature),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(R.string.nature),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
            )
        }
        Column(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.cat_cultural),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(60.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(R.string.cultural),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.paddingFromBaseline(top = 16.dp, bottom = 8.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TripCategories() {
    TraleCapstoneTheme {
        TripCategories(
            { },
        )
    }
}