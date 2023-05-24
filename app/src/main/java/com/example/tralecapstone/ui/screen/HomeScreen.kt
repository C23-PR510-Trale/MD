package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.R
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.components.EmergencyNumber
import com.example.tralecapstone.ui.components.SearchBar
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.DarkGrey
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.viewmodel.HomeViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllPlanTrips()
            }
            is UiState.Success -> {
                HomeContent(
                    planList = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    planList: List<PlanTrip>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
) {

    val searchResult by viewModel.plantrips.collectAsState()
    val query by viewModel.query

    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()
        .verticalScroll(state = rememberScrollState(), enabled = true),
    ) {
        Box{
            Image(
                painter = painterResource(id = R.drawable.home_background),
                modifier = modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Column {
                SearchBar(query = query, onQueryChange = viewModel::search)
                Text(
                    text = "Welcome!",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Plan Trip Buckets",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterStart)
                    )

                    Text(
                        text = "See All",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        color = DarkGrey,
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterEnd)
                    )
                }

//            LazyHorizontalGrid(
//                rows = GridCells.Adaptive(160.dp),
//                contentPadding = PaddingValues(16.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = modifier
//            ) {
//                items(planList) { data ->
                CardHostsItem(
//                        hostId = data.id,
                    hostId = 0,
//                        image = data.image,
                    image = R.drawable.background,
                    title = "data.title",
//                        price = data.price,
                    price = 100000,
//                        rating = data.rating,
                    rating = 4.5,
//                    category = data.category,
                    category = "culinary",
//                    openStatus = data.openStatus,
                    openStatus = "Open",
                    navigateToDetail = {
                        navigateToDetail(it)
                    }
                )
//                }
//            }
                Text(
                    text = "Your Trip Went Wrong? Let Us Know!",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(top = 30.dp, start = 20.dp, bottom = 20.dp)
                )

                EmergencyNumber(navigateToDetail = {})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TraleCapstoneTheme {
        HomeScreen(navigateToDetail = {})
    }
}