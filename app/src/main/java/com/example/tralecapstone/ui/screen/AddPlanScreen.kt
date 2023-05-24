package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme

@Composable
fun AddPlanScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.background),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column{
            Text(
                "stringResource(id = R.string.stu_bangkit)",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
            Text(
                "stringResource(id = R.string.bangkit_email)",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPlanScreenPreview() {
    TraleCapstoneTheme() {
        ProfileScreen(navigateBack = { })
    }
}