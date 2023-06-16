package com.example.tralecapstone.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.components.FilledButton
import com.example.tralecapstone.ui.components.TextFields
import com.example.tralecapstone.ui.theme.Orange400
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentDetailScreen(
    navigateBack: () -> Unit,
    navigateToConfPayment: () -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
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
                text = "Payment Details",
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
                .padding(start = 14.dp, end = 24.dp, top = 30.dp, bottom = 50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
            elevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "Scan QRIS QR Code",
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )

                Image(
                    painter = painterResource(R.drawable.qrcode),
                    contentDescription = "qrcode for qris payment",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp)
                        .align(CenterHorizontally)
                        .clip(RoundedCornerShape(20.dp))
                )

                Text(
                    text = "Or",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )

                Text(
                    text = "Enter your Credit Card Details",
                    overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.align(CenterHorizontally)
                )

                var name by remember { mutableStateOf("") }
                var cardNumb by remember { mutableStateOf("") }
                val focusManager = LocalFocusManager.current

                TextFields(
                    value = name,
                    onValueChange = { name = it },
                    label = "Card Holder Name",
                    color = Yellow,
                    leadingIconImageVector = Icons.Rounded.AccountBox,
                    leadingIconDescription = "input the card holder name",
                    showError = !validateDataRegis(data = name),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    errorMessage = "Fill In Card Holder Name"
                )


                TextFields(
                    value = cardNumb,
                    onValueChange = { cardNumb = if (it == "") "0" else it },
                    label = "Card Number",
                    color = Yellow,
                    leadingIconImageVector = Icons.Rounded.CreditCard,
                    leadingIconDescription = "input your card number",
                    showError = !validateDataRegis(data = cardNumb),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ), //
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )
            }
        }

        FilledButton(
            text = "Confirm Your Payment",
            color = Orange400,
            enable = true,
            modifier = Modifier.padding(horizontal = 20.dp),
            onClick = navigateToConfPayment
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PaymentDetailScreenPreview() {
    TraleCapstoneTheme {
        PaymentDetailScreen(navigateBack = {}, navigateToConfPayment = {})
    }
}
