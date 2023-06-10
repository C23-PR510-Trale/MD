package com.example.tralecapstone.ui.screen

import android.content.Context
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tralecapstone.R
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.model.Facilities
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.model.Trips
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.components.FilledButton
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.Orange400
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
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
fun PaymentScreen(
    idPlan: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navController: NavController,
    navigateBack: () -> Unit,
    navigateToPaymentDetails: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getPlanById(idPlan)
            }
            is UiState.Success -> {
                PaymentContent(
                    planTrip = uiState.data.planTrip,
//        planTrip = PlanTrip(
//            0,
//            R.drawable.background,
//            "title",
//            100000,
//            4.5,
//            "Culinary",
//            "Open",
//            listOf(Trips(0, "Barongsai", R.drawable.logo_twitter, "desc", 500000)),
//            Facilities(0, true, true, true),
//        ),
                    modifier = modifier,
                    navigateBack = navigateBack,
                    navigateToDetail = navigateToDetail,
                    navController = navController,
                    navigateToPaymentDetails = navigateToPaymentDetails
                )
            }
            is UiState.Error -> {}
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentContent(
    planTrip: PlanTrip,
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateBack: () -> Unit,
    navigateToPaymentDetails: () -> Unit,
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
                text = "Payment Confirmation",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 10.dp, vertical = 16.dp)
            )
        }

        CardHostsItem(
            hostId = planTrip.id,
            image = planTrip.image,
            title = planTrip.title,
            price = planTrip.price,
            rating = planTrip.rating,
            category = planTrip.category,
            openStatus = planTrip.openStatus,
            navigateToDetail = {
                navigateToDetail(it)
            }
        )

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
                    text = "Payment Details",
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                    ),
                    fontSize = 16.sp,
                )

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Price",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp,
                    )
                    Text(
//                        text = stringResource(R.string.price, 500000),
                        text = stringResource(R.string.price, planTrip.price),
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Admin",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = stringResource(R.string.price, 5000),
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Unique Code",
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp,
                    )
                    Text(
                        text = stringResource(R.string.price, 321),
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Text(
            text = "Transfer Here",
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 20.dp, bottom = 6.dp)
        )

        Text(
            text = "1234 567 891 011 a.n Aghni Qisthi",
            color = MaterialTheme.colors.primary,
            fontSize = 15.sp,
            modifier = Modifier.padding(start = 20.dp, bottom = 30.dp)
        )

        val dateTime = LocalDateTime.now().plusDays(1)

        Text(
            text = "Expired on ${dateTime.dayOfWeek}, ${dateTime.dayOfMonth} ${dateTime.month} ${dateTime.year} on ${dateTime.hour}:${dateTime.minute}",
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 20.dp, bottom = 50.dp)
        )

        FilledButton(
            text = "Pay Here",
            color = Orange400,
            enable = true,
            modifier = Modifier.padding(horizontal = 20.dp),
            onClick = navigateToPaymentDetails
        )
    }
}
