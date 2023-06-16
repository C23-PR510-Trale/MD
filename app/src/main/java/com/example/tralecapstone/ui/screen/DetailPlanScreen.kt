package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.components.FilledButton
import com.example.tralecapstone.ui.theme.Orange400
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun DetailPlanScreen(
    budget:Int,
    preference:String,
    numrows:Int,
    idPlan: Int,
    title: String,
    price: Double,
    rating: Double,
    location: String,
    onMessageClicked: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    navigateToBooking: (Int, String, Int, Int, String, Float, Float, String) -> Unit,
    ) {
    DetailContent(
        budget=budget,
        preference=preference,
        numrows=numrows,
        planId = idPlan,
        title = title,
        price = price,
        rating = rating,
        location = location,
        category = preference,
        modifier = modifier,
        navigateToBooking = navigateToBooking,
        navigateBack = navigateBack,
        onMessageClicked = onMessageClicked
    )
}

@Composable
fun DetailContent(
    budget:Int,
    preference:String,
    numrows:Int,
    planId:Int,
    title: String,
    price: Double,
    rating: Double,
    location: String,
    category: String,
    modifier: Modifier = Modifier,
    onMessageClicked: (String) -> Unit,
    navigateBack: () -> Unit,
    navigateToBooking: (Int, String, Int, Int, String, Float, Float, String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
//            .verticalScroll(state = rememberScrollState(), enabled = true)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = stringResource(id = R.string.click_back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "Detail Trip Plan",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 10.dp, vertical = 16.dp)
            )
        }

        Card(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
                .clickable { }
                .padding(10.dp),
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            elevation = 4.dp
        ) {
            Column(
                modifier = modifier
                    .padding(20.dp)
            ) {
                Text(
                    text = title,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.primary,
                    ),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                )
                Row {
                    Column {
                        Text(
                            text = "Category",
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.subtitle2,
                        )
                        Text(
                            text = "Budget",
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.subtitle2,
                        )
                        Text(
                            text = "Rating",
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.subtitle2,
                        )
                        Text(
                            text = "Location",
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.subtitle2,
                        )
                    }
                    Column(
                        modifier = Modifier.padding(horizontal = 30.dp)
                    ) {
                        Text(
                            text = category,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 14.sp
                        )
                        Text(
                            text = stringResource(R.string.price, price),
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.SemiBold,
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 14.sp
                        )
                        Text(
                            text = rating.toString(),
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 14.sp
                        )
                        Text(
                            text = location,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.primary,
                            style = MaterialTheme.typography.subtitle2,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        FilledButton(
            text = "Book This Trip",
            color = Orange400,
            enable = true,
            modifier = Modifier.padding(vertical = 20.dp),
            onClick = { navigateToBooking(budget, preference, numrows, planId, title, price.toFloat(), rating.toFloat(), location) }
        )
        val shareMessage = "I want to ask about $title plan trip"

        FilledButton(
            text = "Message Host",
            color = Orange400,
            enable = true,
            onClick = {
                onMessageClicked(shareMessage)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPlanScreenPreview() {
    TraleCapstoneTheme {
//        DetailPlanScreen(
//            0, "", 0, 0.0, "", "",
//            navigateToBooking = {},
//            navigateBack = {},
//            onMessageClicked = {},
//        )
    }
}