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
import com.example.tralecapstone.ui.components.*
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.DarkGrey
import com.example.tralecapstone.ui.theme.Orange300
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.viewmodel.CommunityViewModel
import com.example.tralecapstone.viewmodel.HistoryViewModel
import com.example.tralecapstone.viewmodel.HomeViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory

@Composable
fun CommunityScreen(
    modifier: Modifier = Modifier,
    viewModel: CommunityViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllPosts()
            }
            is UiState.Success -> {
                CommunityContent(
                    posts = uiState.data,
                    modifier = modifier,
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun CommunityContent(
    posts: List<Post>,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
) {

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

            Text(
                text = "Our Community",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp)
            )

        var content by remember{ mutableStateOf("") }

        AddPost(
            query = content,
            onQueryChange = { content = it }
        )

        Button(
            colors = ButtonDefaults.buttonColors(Yellow),
            onClick = {

            },
            shape = RoundedCornerShape(10.dp),
            enabled = true,
            modifier = modifier
                .align(Alignment.End)
                .wrapContentSize()
                .padding(vertical = 4.dp, horizontal = 26.dp)
        ) {
            Text(
                text = "Send",
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
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
            CardPostItem(
//                        postId = data.id,
                postId = 0,
                name = "data.title",
                content = "data.content"
            )
//                }
//            }
    }
}

@Preview(showBackground = true)
@Composable
fun CommunityScreenPreview() {
    TraleCapstoneTheme {
        CommunityScreen()
    }
}