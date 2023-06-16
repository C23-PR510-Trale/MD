//package com.example.tralecapstone.ui.screen
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CornerSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.rounded.ArrowBackIos
//import androidx.compose.material.icons.rounded.Reviews
//import androidx.compose.material.icons.rounded.Star
//import androidx.compose.material.icons.rounded.StarOutline
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment.Companion.CenterVertically
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.tralecapstone.R
//import com.example.tralecapstone.di.Injection
//import com.example.tralecapstone.model.repository.PlanTripRepository
//import com.example.tralecapstone.model.data.Trips
//import com.example.tralecapstone.ui.components.*
//import com.example.tralecapstone.ui.theme.DarkGrey
//import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
//import com.example.tralecapstone.ui.theme.Yellow
//import com.example.tralecapstone.viewmodel.DetailViewModel
//import com.example.tralecapstone.viewmodel.ViewModelFactory
//
//@Composable
//fun RatingTripScreen(
//    idPlan: Int,
//    modifier: Modifier = Modifier,
//    viewModel: DetailViewModel = viewModel(
//        factory = ViewModelFactory(Injection.provideRepositoryPlanTrip())
//    ),
//    navigateBack: () -> Unit,
//) {
////    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
////        when (uiState) {
////            is UiState.Loading -> {
////                viewModel.getPlanById(idPlan)
////            }
////            is UiState.Success -> {
//    RatingTripContent(
////                    planTrip = uiState.data,
//        planTrip = PlanTrip(
//            0,
//            R.drawable.background,
//            "title",
//            100000,
//            4.5,
//            "Culinary",
//            "Open",
//            listOf(Trips(0, "Barongsai", R.drawable.logo_twitter, "desc", 500000)),
//            Facilities(0, true, true, true),
//        ),
//        modifier = modifier,
//        navigateBack = navigateBack,
//    )
////            }
////            is UiState.Error -> {}
////        }
////    }
//}
//
//@Composable
//fun RatingTripContent(
//    planTrip: PlanTrip,
//    modifier: Modifier = Modifier,
//    navigateBack: () -> Unit,
//    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(PlanTripRepository())),
//) {
//
//    Column(
//        modifier = Modifier
//            .background(Color.White)
//            .fillMaxSize()
//    ) {
//        Box(modifier = Modifier.fillMaxWidth()) {
//            Icon(
//                imageVector = Icons.Rounded.ArrowBackIos,
//                contentDescription = stringResource(id = R.string.click_back),
//                modifier = Modifier
//                    .padding(16.dp)
//                    .clickable { navigateBack() }
//                    .align(Alignment.CenterStart)
//            )
//            Text(
//                text = "How was your trip?",
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.Center)
//                    .padding(horizontal = 10.dp, vertical = 16.dp)
//            )
//        }
//
//
//        Card(
//            modifier = Modifier
//                .padding(24.dp)
//                .fillMaxWidth(),
//            shape = RoundedCornerShape(corner = CornerSize(20.dp)),
//            elevation = 4.dp
//        ) {
//            Column(modifier = Modifier.padding(14.dp)) {
//                Row {
//                    Image(
//                        painter = painterResource(planTrip.image),
//                        contentDescription = null,
//                        contentScale = ContentScale.FillBounds,
//                        modifier = Modifier
//                            .size(90.dp)
//                            .clip(RoundedCornerShape(20.dp))
//                    )
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 14.dp)
//                            .align(CenterVertically)
//                    ) {
//                        Text(
//                            text = planTrip.title,
//                            maxLines = 3,
//                            overflow = TextOverflow.Ellipsis,
//                            style = MaterialTheme.typography.subtitle1.copy(
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colors.primary,
//                            ),
//                            fontSize = 16.sp,
//                        )
//                        Text(
//                            text = stringResource(R.string.price, planTrip.price),
//                            color = MaterialTheme.colors.primary,
//                            style = MaterialTheme.typography.subtitle2,
//                            fontSize = 14.sp,
//                        )
//
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight()
//                        ) {
//                            Text(
//                                text = planTrip.rating.toString(),
//                                fontWeight = FontWeight.SemiBold,
//                                color = MaterialTheme.colors.primary,
//                                fontSize = 14.sp,
////                                modifier = Modifier.padding(top = 3.dp)
//                            )
//                            Icon(
//                                imageVector = Icons.Default.Star,
//                                contentDescription = null,
//                                tint = MaterialTheme.colors.primary,
//                                modifier = Modifier
//                                    .size(18.dp)
////                                    .padding(start = 4.dp)
////                                    .align(CenterVertically)
//                            )
//
//                            Spacer(
//                                modifier = Modifier
//                                    .padding(horizontal = 10.dp)
//                                    .size(2.dp, 14.dp)
//                                    .background(MaterialTheme.colors.primary)
////                                    .align(CenterVertically)
//                            )
//
//                            Text(
//                                text = planTrip.category,
//                                fontWeight = FontWeight.SemiBold,
//                                color = MaterialTheme.colors.primary,
//                                fontSize = 14.sp,
////                                modifier = Modifier.padding(top = 3.dp)
//                            )
//
//                            Spacer(
//                                modifier = Modifier
//                                    .padding(horizontal = 10.dp)
//                                    .size(2.dp, 14.dp)
//                                    .background(MaterialTheme.colors.primary)
////                                    .align(CenterVertically)
//                            )
//
//                            Text(
//                                text = planTrip.category,
//                                fontWeight = FontWeight.SemiBold,
//                                color = MaterialTheme.colors.primary,
//                                fontSize = 14.sp,
////                                modifier = Modifier.padding(top = 3.dp)
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.padding(vertical = 16.dp))
//
//                Text(
//                    text = "Leave your rating here",
//                    textAlign = TextAlign.Center,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                    modifier = Modifier
//                        .fillMaxWidth()
////                        .align(CenterHorizontally)
//                        .padding(horizontal = 10.dp, vertical = 16.dp)
//                )
//
//                Row(modifier) {
//                    Icon(
//                        imageVector = Icons.Rounded.StarOutline,
//                        tint = Yellow,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .size(30.dp)
//                            .clickable {
////                                    RatingBar(rating = 1)
//                            }
//                    )
//                    Spacer(
//                        modifier = Modifier
//                            .size(10.dp)
////                            .align(Alignment.CenterVertically)
//                    )
//                }
//
//                Text(
//                    text = "Feedback",
//                    fontWeight = FontWeight.SemiBold,
//                    color = DarkGrey,
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(top = 20.dp, start = 20.dp)
//                )
//
//                var feedback by remember { mutableStateOf("") }
//                val focusManager = LocalFocusManager.current
//
//                TextFields(
//                    value = feedback,
//                    onValueChange = { feedback = it },
//                    label = "Leave your feedback here",
//                    color = Yellow,
//                    leadingIconImageVector = null,
//                    leadingIconDescription = "input feedback",
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Text,
//                        imeAction = ImeAction.Done
//                    ),
//                )
//
//                FilledButton(
//                    text = "Submit", color = Yellow, onClick = {},
//                    enable = true
//                )
//            }
//        }
//    }
//}
//
//@Composable
//private fun RatingBar(
//    modifier: Modifier = Modifier,
//    rating: Int = 0,
//) {
//
//    Row(modifier) {
//        for (i in 1..rating) {
//            Icon(
//                imageVector = Icons.Rounded.Star,
//                tint = Yellow,
//                contentDescription = null,
//                modifier = Modifier
//                    .size(30.dp)
//            )
//            Spacer(
//                modifier = Modifier
//                    .size(10.dp)
//                    .align(Alignment.CenterVertically)
//            )
//        }
//
//        for (i in 1..(5 - rating)) {
//            Icon(
//                imageVector = Icons.Rounded.StarOutline,
//                tint = Yellow,
//                contentDescription = null,
//                modifier = Modifier.size(30.dp)
//            )
//            Spacer(
//                modifier = Modifier
//                    .size(10.dp)
//                    .align(Alignment.CenterVertically)
//            )
//        }
//
//        Icon(
//            imageVector = Icons.Rounded.Reviews,
//            contentDescription = null,
//            tint = Color.LightGray,
//            modifier = Modifier
//                .size(28.dp)
//                .padding(start = 10.dp)
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun RatingTripScreenPreview() {
//    TraleCapstoneTheme {
//        RatingTripScreen(0, navigateBack = {})
//    }
//}