package com.example.tralecapstone.ui.screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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
import androidx.navigation.NavController
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.R
import com.example.tralecapstone.model.Data
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.components.EmergencyNumber
import com.example.tralecapstone.ui.components.SearchBar
import com.example.tralecapstone.ui.navigation.Screen
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
    navController: NavController
) {
    val searchResult by viewModel.plantrips.collectAsState()
    val query by viewModel.query

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true),
    ) {
        Box {
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
                            .clickable {
                                navController.navigate(Screen.TripRecommendation.route)
                            }
                    )
                }
                viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            viewModel.getAllPlanTrips()
                        }
                        is UiState.Success -> {
                            HomeContent(
                                listData = uiState.data,
                                modifier = modifier,
                                navigateToDetail = navigateToDetail,
                                navController = navController
                            )
                        }
                        is UiState.Error -> {
                            Text(
                                text = uiState.errorMessage,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(top = 30.dp, start = 20.dp, bottom = 20.dp)
                            )
                        }
                    }
                }
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

@Composable
fun HomeContent(
    listData: List<Data>,
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(listData) { data ->
            CardHostsItem(
                hostId = data.planTrip.id,
                image = data.planTrip.image,
                title = data.planTrip.title,
                price = data.planTrip.price,
                rating = data.planTrip.rating,
                category = data.planTrip.category,
                openStatus = data.planTrip.openStatus,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    TraleCapstoneTheme {
//        HomeScreen(navigateToDetail = {})
    }
}