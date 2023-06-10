package com.example.tralecapstone.ui.screen

import android.os.Build
import android.widget.DatePicker
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tralecapstone.R
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.model.Facilities
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.model.Trips
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.components.FilledButton
import com.example.tralecapstone.ui.components.TextFields
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.Orange400
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.viewmodel.DetailViewModel
import com.example.tralecapstone.viewmodel.HistoryViewModel
import com.example.tralecapstone.viewmodel.HomeViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory
import okhttp3.internal.uppercase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentDetailScreen(
    idPlan: Int,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
//    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
//        when (uiState) {
//            is UiState.Loading -> {
//                viewModel.getPlanById(idPlan)
//            }
//            is UiState.Success -> {
    PaymentDetailContent(
//                    planTrip = uiState.data,
        planTrip = PlanTrip(
            0,
            R.drawable.background,
            "title",
            100000,
            4.5,
            "Culinary",
            "Open",
            listOf(Trips(0, "Barongsai", R.drawable.logo_twitter, "desc", 500000)),
            Facilities(0, true, true, true),
        ),
        modifier = modifier,
        navigateBack = navigateBack,
        navigateToDetail = navigateToDetail,
    )
//            }
//            is UiState.Error -> {}
//        }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentDetailContent(
    planTrip: PlanTrip,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
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
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                    ),
                    fontSize = 16.sp,
                )

                Image(
                    painter = painterResource(R.drawable.qrcode),
                    contentDescription = "qrcode for qris payment",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                Text(
                    text = "Or",
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp,
                )

                Text(
                    text = "Enter your Credit Card Details",
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                    ),
                    fontSize = 16.sp,
                )

                var name by remember { mutableStateOf("") }
                var cardNumb by remember { mutableStateOf(0) }
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
                    value = cardNumb.toString(),
                    onValueChange = {cardNumb = if(it == "") 0 else it.toInt()},
                    label = "Card Number",
                    color = Yellow,
                    leadingIconImageVector = Icons.Rounded.CreditCard,
                    leadingIconDescription = "input your card number",
                    showError = !com.example.tralecapstone.ui.components.validateDataRegis(data = cardNumb.toString()),
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
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentDetailScreenPreview() {
    TraleCapstoneTheme {
        PaymentDetailScreen(0, navigateToDetail = {}, navigateBack = {})
    }
}
