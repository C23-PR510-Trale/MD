package com.example.tralecapstone.ui.screen

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Paid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.components.OutlinedButton
import com.example.tralecapstone.ui.components.TextFields
import com.example.tralecapstone.ui.theme.Orange300
import com.example.tralecapstone.ui.theme.Yellow
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddPlanScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val date = remember { mutableStateOf("Choose Your Date") }

    val datePickerDialog = DatePickerDialog(
        context, { _, year, month, day ->
            date.value = "$day/$month/$year"
        }, year, month, day
    )

    var budget by remember{ mutableStateOf(0) }
    var destination by remember{ mutableStateOf("") }
    var pref1 by remember{ mutableStateOf("") }
    var pref2 by remember{ mutableStateOf("") }

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.background),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBackIos,
                    contentDescription = stringResource(id = R.string.click_back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { navigateBack() }
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = "Let us know about you",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 10.dp, vertical = 16.dp)
                )
            }

            OutlinedButton(
                onClick = {
                    datePickerDialog.show()
                },
                text = date.value,
                color = Yellow,
                enable = true,
                modifier = Modifier.padding(20.dp)
            )

            TextFields(
                value = budget.toString(),
                onValueChange = {budget = it.toInt()},
                label = "Budget",
                color = Yellow,
                leadingIconImageVector = Icons.Rounded.Paid,
                leadingIconDescription = "input your budget",
                showError = !com.example.tralecapstone.ui.components.validateDataRegis(data = budget.toString()),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ), //
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun AddPlanScreenPreview() {
    TraleCapstoneTheme() {
        AddPlanScreen(navigateBack = { })
    }
}