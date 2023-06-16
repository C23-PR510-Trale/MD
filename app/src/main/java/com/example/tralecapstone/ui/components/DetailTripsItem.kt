package com.example.tralecapstone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun DetailTripsItem(
    image: Int,
    title: String,
    desc: String,
    price: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
    ) {
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold,
                color = DarkGray,
            ),
            fontSize = 16.sp,
        )
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .sizeIn(maxWidth = 300.dp, maxHeight = 200.dp)
                .align(CenterHorizontally)
                .padding(vertical = 20.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Text(
            text = desc,
            color = DarkGray,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Text(
            text = stringResource(R.string.price, price),
            color = DarkGray,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailTripsItemPreview() {
    TraleCapstoneTheme {
        DetailTripsItem(
            image = R.drawable.ic_launcher_background,
            title = "Title Plan Hosts",
            price = 500000,
            desc = "Culinary",
        )
    }
}