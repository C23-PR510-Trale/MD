package com.example.tralecapstone.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.Poppins
import com.example.tralecapstone.ui.theme.Shapes
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun CardHostsItem(
    hostId: Int,
    image: Int,
    title: String,
    price: Int,
    rating: Double,
    category: String,
    openStatus: String,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth()
            .clickable { }
            .padding(10.dp),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = 4.dp
    ){
        Row(modifier = Modifier.padding(14.dp)) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
                    .weight(1.0f)
                    .align(CenterVertically)
            ) {
                Text(
                    text = title,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                    ),
                    fontSize = 16.sp,
                )
                Text(
                    text = stringResource(R.string.price, price),
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp,
                )
                Row(
                    modifier = Modifier.weight(1.0f)
                ) {
                    Text(
                        text = rating.toString(),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier.size(16.dp).align(CenterVertically)
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp,)
                            .size(2.dp, 14.dp)
                            .background(MaterialTheme.colors.primary)
                    )

                    Text(
                        text = category,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp,)
                            .size(2.dp, 14.dp)
                            .background(MaterialTheme.colors.primary)
                    )

                    Text(
                        text = openStatus,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                    )

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardHostsPreview() {
    TraleCapstoneTheme() {
        CardHostsItem(
            hostId = 1,
            image = R.drawable.logo_app,
            title = "Title Plan Hosts",
            price = 500000,
            rating = 3.0,
            category = "Culinary",
            openStatus = "Open",
            navigateToDetail = { },
        )
    }
}