package com.example.tralecapstone.ui.screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.tralecapstone.model.request.AddPlanRequest
import com.example.tralecapstone.model.response.AddPlanResponse
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.viewmodel.ListPlanViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ListTripScreen(
    title: String,
    budget: Int,
    preference: String,
    numrows: Int,
    modifier: Modifier = Modifier,
    viewModel: ListPlanViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepositoryPlanTrip())
    ),
    navigateBack: () -> Unit,
    navigateToDetail: (Int, String, Int, Int, String, Float, Float, String) -> Unit,
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
                text = title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = 10.dp, vertical = 16.dp)
            )
        }
        viewModel.listPlan.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllList(
                        AddPlanRequest(
                            budget = budget,
                            category = preference,
                            num_of_recom = numrows
                        )
                    )
                    GlideImage(
                        model = R.drawable.loading_text,
                        contentDescription = "loading confirmation page",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
                }
                is UiState.Success -> {
                    ListTripContent(
                        planList = uiState.data,
                        category = preference,
                        budget = budget,
                        numrows = numrows,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                    )
                }
                is UiState.Error -> {}
            }
        }
    }
}

@Composable
fun ListTripContent(
    category: String,
    budget: Int,
    numrows: Int,
    planList: AddPlanResponse,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int, String, Int, Int, String, Float, Float, String) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        for (i in 0 until planList.prediction.trip_id.size) {
            item {
                Log.d("cek item card", planList.prediction.trip_name[i])
                CardHostsItem(
                    hostId = planList.prediction.trip_id[i],
                    title = planList.prediction.trip_name[i],
                    price = planList.prediction.budget[i],
                    rating = planList.prediction.rating[i],
                    budget = budget,
                    numrows = numrows,
                    category = category,
                    location = planList.prediction.location[i],
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListTripScreenPreview() {
    TraleCapstoneTheme {
//        ListTripScreen(navigateToDetail = {}, navigateBack = {}, title = "History Trip")
    }
}