package com.example.tralecapstone.ui.components

import android.util.Log
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.Poppins
import com.example.tralecapstone.ui.theme.Shapes
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun CardHostsItem(
    hostId: Int,
    title: String,
    price: Double,
    rating: Double,
    budget:Int,
    category: String,
    numrows:Int,
    location: String,
    navigateToDetail: (Int, String, Int, Int, String, Float, Float, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
//            .width(300.dp)
            .fillMaxWidth()
            .clickable {
                Log.d("cek cardhost click", price.toString())
                navigateToDetail(
                    budget,
                    category,
                    numrows,
                    hostId,
                    title,
                    price.toFloat(),
                    rating.toFloat(),
                    location,
                )
            },
        shape = RoundedCornerShape(corner = CornerSize(20.dp)),
        elevation = 4.dp
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
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

                    Spacer(modifier = Modifier.padding(10.dp))

                }
            }
    }
}

@Composable
@Preview(showBackground = true)
fun CardHostsPreview() {
    TraleCapstoneTheme() {
//        CardHostsItem(
//            0,
//            title = "Title Plan Hosts",
//            price = 500000,
//            rating = 3.0,
//            category = "Culinary",
//            location = "Jakarta",
//            navigateToDetail = ,
//        )
    }
}