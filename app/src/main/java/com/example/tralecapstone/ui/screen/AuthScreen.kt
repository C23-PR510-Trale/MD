package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tralecapstone.ui.navigation.TabItem
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.components.Login
import com.example.tralecapstone.ui.theme.*
import com.example.tralecapstone.viewmodel.AuthViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthScreen(modifier: Modifier = Modifier, navigateBack : () -> Unit) {

    val list = listOf(TabItem.Register, TabItem.Login)
    val pagerState = rememberPagerState(initialPage = 0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(200.dp)
                .align(CenterHorizontally)
                .padding(vertical = 30.dp)
        )
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState)

        val status : Boolean = Login()
        if(status) navigateBack()
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        divider = {},
        modifier = Modifier
            .shadow(
                20.dp,
                shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp),
                true,
                DarkGrey,
                DarkGrey
            )
            .clip(RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        }) {
        tabs.forEachIndexed { index, tabItem ->

            LeadingIconTab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(tabItem.title, fontWeight = FontWeight.SemiBold) },
                icon = { },
                selectedContentColor = Orange400,
                unselectedContentColor = DarkGrey,
                enabled = true
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].screens()
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    TraleCapstoneTheme {
        AuthScreen(navigateBack = {})
    }
}