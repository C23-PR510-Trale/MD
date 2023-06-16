package com.example.tralecapstone.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun CardPayment(
    price: Int,
    admin: Int,
    code: Int,
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
    ) {
        Column(
            modifier = modifier
                .padding(20.dp)
        ) {
            Text(
                text = "Ringkasan Pembayaran",
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
                        text = "Price",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                    )
                    Text(
                        text = "Admin",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                    )
                    Text(
                        text = "Unique Code",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                    )
                }
                Column(
                    modifier = Modifier.padding(horizontal = 30.dp)
                ) {
                    Text(
                        text = stringResource(R.string.price, price),
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp
                    )
                    Text(
                        text = stringResource(R.string.price, admin),
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp
                    )
                    Text(
                        text = stringResource(R.string.price, code),
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardPaymentPreview() {
    TraleCapstoneTheme {
        CardPayment(
            price = 500000,
            admin = 3000,
            code = 135,
        )
    }
}