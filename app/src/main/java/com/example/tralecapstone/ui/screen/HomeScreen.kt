package com.example.tralecapstone.ui.screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tralecapstone.R
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.model.request.AddPlanRequest
import com.example.tralecapstone.model.response.AddPlanResponse
import com.example.tralecapstone.model.response.Prediction
import com.example.tralecapstone.model.response.TripsResponse
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.components.EmergencyNumber
import com.example.tralecapstone.ui.components.SearchBar
import com.example.tralecapstone.ui.components.dataStore
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.DarkGrey
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.viewmodel.HomeViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory
import com.example.tralecapstone.viewmodel.ViewModelFactoryAuth

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactoryAuth(Injection.provideRepositoryAuth(LocalContext.current.dataStore))
    ),
    navigateToDetail: (Int,String, Int, Int, String, Float, Float, String) -> Unit,
    navController: NavController
) {
//    val viewModel: HomeViewModel by viewModel()
//    val searchResult by viewModel.plantrips.collectAsState()
    val query by viewModel.query

    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState(), enabled = true),
    ) {
        Box {
            if(isSystemInDarkTheme()){

            } else {
                Image(
                    painter = painterResource(id = R.drawable.home_background),
                    modifier = modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
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
                viewModel.listPlan.collectAsState(initial = UiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            viewModel.getAllPlanTrips()
                            GlideImage(
                                model = R.drawable.loading_text,
                                contentDescription = "loading confirmation page",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                        is UiState.Success -> {
                            HomeContent(
                                listData = uiState.data,
                                modifier = modifier,
                                navigateToDetail = navigateToDetail,
                                navController = navController,
                                category = "Culinary",
                                budget = 500000,
                                numrows = 20
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

                EmergencyNumber()
            }
        }
    }
}

@Composable
fun HomeContent(
    listData: TripsResponse,
    category :String,
    budget :Int,
    numrows :Int,
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToDetail: (Int, String,Int, Int, String, Float, Float, String) -> Unit,
//    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
) {
    LazyRow(
        contentPadding = PaddingValues(24.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
    ) {
            items(listData.data) {
                CardHostsItem(
                    hostId = it.id,
                    title = it.trip_name,
                    price = it.budget.toDouble(),
                    rating = it.rating,
                    budget = it.budget,
                    numrows = numrows,
                    category = category,
                    location = it.location,
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