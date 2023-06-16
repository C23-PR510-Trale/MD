package com.example.tralecapstone.ui.screen.sidefiture

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.tralecapstone.model.response.VoluntripResponse
import com.example.tralecapstone.ui.components.*
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.viewmodel.*

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun VoluntripScreen(
    modifier: Modifier = Modifier,
    viewModel: VoluntripViewModel = viewModel(
        factory = ViewModelFactoryAuth(Injection.provideRepositoryAuth(LocalContext.current.dataStore))
    ),
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
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getAllVoluntrip()
                    GlideImage(
                        model = R.drawable.loading_text,
                        contentDescription = "loading confirmation page",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally)
                    )
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
}

@Composable
fun VoluntripContent(
    voluntrip: VoluntripResponse,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ){
        items(voluntrip.data) { data ->
            CardVoluntripItem(
                event_name = data.event_name,
                description = data.description,
                price = data.price,
                rating = data.rating,
                category = data.category,
                city = data.city,
                pelaksanaan = data.pelaksanaan,
                regis = data.regis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VoluntripScreenPreview() {
    TraleCapstoneTheme {
        VoluntripScreen()
    }
}