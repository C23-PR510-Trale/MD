package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
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
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.R
import com.example.tralecapstone.model.Data
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.ui.components.CardHostsItem
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.viewmodel.HistoryViewModel
import com.example.tralecapstone.viewmodel.HomeViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory

@Composable
fun ListTripScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllHistory()
            }
            is UiState.Success -> {
                ListTripContent(
                    planList = uiState.data,
                    modifier = modifier,
                    navigateBack = navigateBack,
                    navigateToDetail = navigateToDetail,
                    title = "Trip History"
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun ListTripContent(
    title: String,
    planList: List<Data>,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
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

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ) {
                items(planList) { data ->
                    CardHostsItem(
                        hostId = data.planTrip.id,
                        image = data.planTrip.image,
                        title = data.planTrip.title,
                        price = data.planTrip.price,
                        rating = data.planTrip.rating,
                        category = data.planTrip.category,
                        openStatus = data.planTrip.openStatus,
                        navigateToDetail = {
                            navigateToDetail(it)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListTripScreenPreview() {
    TraleCapstoneTheme {
        ListTripScreen(navigateToDetail = {}, navigateBack = {})
    }
}