package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.tralecapstone.R
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.model.repository.PlanTripRepository
import com.example.tralecapstone.model.response.AddPlanResponse
import com.example.tralecapstone.model.response.TripsResponse
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.components.TripCategories
import com.example.tralecapstone.ui.components.dataStore
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.viewmodel.ListPlanViewModel
import com.example.tralecapstone.viewmodel.HomeViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory
import com.example.tralecapstone.viewmodel.ViewModelFactoryAuth

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TripRecommendationScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactoryAuth(Injection.provideRepositoryAuth(LocalContext.current.dataStore))
    ),
    navigateBack: () -> Unit,
    navigateToDetail: (Int, String, Int, Int, String, Float, Float, String) -> Unit,
    navigateToListTrip: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = stringResource(id = R.string.click_back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
                    .align(Alignment.CenterStart)
            )
            Text(
                text = "Trip Recommendation",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 10.dp, vertical = 16.dp)
            )
        }

        TripCategories(
            modifier = modifier
                .align(CenterHorizontally)
                .padding(vertical = 20.dp),
            onClick = navigateToListTrip
        )

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
                    TripRecommendationContent(
                        listData = uiState.data,
                        modifier = modifier,
                        navigateBack = navigateBack,
                        navigateToDetail = navigateToDetail,
                        navigateToListTrip = navigateToListTrip,
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun TripRecommendationContent(
    listData: TripsResponse,
//    category :String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetail: (Int, String, Int, Int, String, Float, Float, String) -> Unit,
    navigateToListTrip: (String) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactoryAuth(Injection.provideRepositoryAuth(LocalContext.current.dataStore))
    )
) {
    LazyColumn(
//            contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        items(listData.data) {
            CardHostsItem(
                hostId = it.id,
                title = it.trip_name,
                price = it.budget.toDouble(),
                rating = it.rating,
                budget = it.budget,
                numrows = 20,
                category = it.category,
                location = it.location,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripRecommendationScreenPreview() {
    TraleCapstoneTheme {
//        TripRecommendationScreen(navigateToDetail = {}, navigateBack = {}, navigateToListTrip = {})
    }
}