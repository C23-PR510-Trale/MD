package com.example.tralecapstone.ui.screen.sidefiture

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.example.tralecapstone.model.Post
import com.example.tralecapstone.model.Voluntrip
import com.example.tralecapstone.ui.components.*
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.DarkGrey
import com.example.tralecapstone.ui.theme.Orange300
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.viewmodel.*

@Composable
fun VoluntripScreen(
    modifier: Modifier = Modifier,
    viewModel: VoluntripViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllVoluntrip()
            }
            is UiState.Success -> {
                VoluntripContent(
                    voluntrip = uiState.data,
                    modifier = modifier,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun VoluntripContent(
    voluntrip: List<Voluntrip>,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

            Text(
                text = "Voluntrip",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            )

//            LazyHorizontalGrid(
//                rows = GridCells.Adaptive(160.dp),
//                contentPadding = PaddingValues(16.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = modifier
//            ) {
//                items(planList) { data ->
            CardVoluntripItem(
//                        VtId = data.id,
                VtId = 0,
                title = "data.title",
                desc = "data.desc",
                price = 500000,
                rating = 4.7,
                category = "data.category",
                city = "data.city"
            )
//                }
//            }
    }
}

@Preview(showBackground = true)
@Composable
fun VoluntripScreenPreview() {
    TraleCapstoneTheme {
        VoluntripScreen()
    }
}