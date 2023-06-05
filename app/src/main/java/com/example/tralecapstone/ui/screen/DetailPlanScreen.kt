package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.tralecapstone.ui.components.DetailTripsItem
import com.example.tralecapstone.ui.components.EmergencyNumber
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.DarkGrey
import com.example.tralecapstone.ui.theme.Orange300
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.viewmodel.DetailViewModel
import com.example.tralecapstone.viewmodel.HomeViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory

@Composable
fun DetailPlanScreen(
    idPlan: Int,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToBooking: (Int) -> Unit,
) {
//    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
//        when (uiState) {
//            is UiState.Loading -> {
//                viewModel.getPlanById(idPlan)
//            }
//            is UiState.Success -> {
                DetailContent(
                    dataTrip = PlanTrip(
                        0,
                    R.drawable.background,
                    "title",
                    100000,
                    4.5,
                    "Culinary",
                    "Open",
                        listOf(Trips(0, "Barongsai", R.drawable.logo_twitter, "desc", 500000)),
                        Facilities(0, true, true, true),
                        R.drawable.home_background
                    ),
//                    dataTrip = uiState.data,
                    modifier = modifier,
                    navigateToBooking = navigateToBooking,
                    navigateBack = navigateBack,
                )
//            }
//            is UiState.Error -> {}
//        }
//    }
}

@Composable
fun DetailContent(
    dataTrip: PlanTrip,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToBooking: (Int) -> Unit,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
//            .verticalScroll(state = rememberScrollState(), enabled = true),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Orange300)
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = stringResource(id = R.string.click_back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
                    .align(CenterVertically)
            )
            Text(
                text = "Wahid’s Surabaya Culinary Venture",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterVertically)
                    .padding(horizontal = 14.dp, vertical = 16.dp)
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(dataTrip.trips) { data ->
                DetailTripsItem(
                    placeId = 0,
//                placeId = data.id,
                    image = R.drawable.ic_launcher_foreground,
//                image = data.image,
                    title = "data.title",
                    desc = "m",
                    price = 100000,
                )
            }
        }

        Text(
            text = "Previous Trips",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color.DarkGray,
            ),
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

//        LazyVerticalGrid(
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = modifier
//        ) {
//            items(dataTrip.image) { data ->
//                DetailTripsItem(
//                    placeId = 0,
////                placeId = data.id,
//                    image = R.drawable.ic_launcher_foreground,
////                image = data.image,
//                    title = "data.title",
//                    desc = "m",
//                    price = 100000,
//                )
//            }
//        }


        Text(
            text = "Facilities",
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold,
                color = DarkGray,
            ),
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .align(CenterHorizontally)
        ) {
            if (dataTrip.facilities.wifi) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_wifi),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(70.dp)
                    )
                    Text(
                        text = stringResource(R.string.wifi),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(CenterHorizontally)
                            .padding(vertical = 10.dp)
                    )
                }
            }
            if (dataTrip.facilities.beverage) {
                Column (
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_beverages),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(70.dp)
                    )
                    Text(
                        text = stringResource(R.string.beverages),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(CenterHorizontally)
                            .padding(vertical = 10.dp)

                    )
                }
            }
            if (dataTrip.facilities.wifi) {
                Column(
                    modifier = Modifier.padding(horizontal = 24.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.icon_foods),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(70.dp)
                    )
                    Text(
                        text = stringResource(R.string.food),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(CenterHorizontally)
                            .padding(vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailPlanScreenPreview() {
    TraleCapstoneTheme {
        DetailPlanScreen(
            0,
            navigateToBooking = {},
            navigateBack = {}
        )
    }
}