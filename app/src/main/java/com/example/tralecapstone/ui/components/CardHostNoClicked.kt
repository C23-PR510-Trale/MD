package com.example.tralecapstone.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R

@Composable
fun CardHostsNoCLickedItem(
    title: String,
    price: Double,
    rating: Double,
    category: String,
    location: String,
) {
    Card(
        modifier = Modifier
            .padding(20.dp)
//            .width(300.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = 4.dp
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = title,
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

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = rating.toString(),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(18.dp)
                            .padding(start = 4.dp)
                            .align(CenterVertically)
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(2.dp, 14.dp)
                            .background(MaterialTheme.colors.primary)
                            .align(CenterVertically)
                    )

                    Text(
                        text = category,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(2.dp, 14.dp)
                            .background(MaterialTheme.colors.primary)
                            .align(CenterVertically)
                    )

                    Text(
                        text = location,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
                Spacer(modifier = Modifier.padding(10.dp))

            }
    }
}