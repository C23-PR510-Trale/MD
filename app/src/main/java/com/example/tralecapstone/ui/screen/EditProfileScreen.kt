package com.example.tralecapstone.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.components.FilledButton
import com.example.tralecapstone.ui.components.TextFields
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow

@Composable
fun EditProfileScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        Image(
            painter = painterResource(id = R.drawable.background_profile),
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState(), enabled = true)
                .padding(10.dp),
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBackIos,
                contentDescription = stringResource(id = R.string.click_back),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { navigateBack() }
                    .align(Alignment.Start)
            )

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                modifier = modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .align(CenterHorizontally),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.padding(vertical = 24.dp))

            val context = LocalContext.current
            val focusManager = LocalFocusManager.current
            val scrollState = rememberScrollState()

            var name by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }
            var telephoneNumber by remember { mutableStateOf("") }
            var username by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var instagram by remember { mutableStateOf("") }
            var twitter by remember { mutableStateOf("") }
            var otherSocmed by remember { mutableStateOf("") }
            var bio by remember { mutableStateOf("") }

//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(color = Color.White)
//                    .verticalScroll(state = rememberScrollState(), enabled = true)
//                    .padding(10.dp),
//            ) {

            //Name
            TextFields(
                value = name,
                onValueChange = { name = it },
                label = "Name",
                color = Yellow,
                leadingIconImageVector = Icons.Default.Person,
                leadingIconDescription = "input your name",
                showError = !validateDataRegis(data = name),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                errorMessage = stringResource(id = R.string.name_error)
            )

            //address
            TextFields(
                value = address,
                onValueChange = { address = it },
                label = "Address",
                color = Yellow,
                leadingIconImageVector = Icons.Default.Home,
                leadingIconDescription = "input your address",
                showError = !validateDataRegis(data = address),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                errorMessage = stringResource(id = R.string.address_error)
            )

            //telephone number
            TextFields(
                value = telephoneNumber,
                onValueChange = { telephoneNumber = it },
                label = "Telephone Number",
                color = Yellow,
                leadingIconImageVector = Icons.Default.Phone,
                leadingIconDescription = "input your telephone number",
                showError = !validateDataRegis(data = telephoneNumber),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                errorMessage = stringResource(id = R.string.telpnumb_error)
            )

            //username
            TextFields(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                color = Yellow,
                leadingIconImageVector = Icons.Default.AccountCircle,
                leadingIconDescription = "input your username",
                showError = !validateDataRegis(data = username),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                errorMessage = stringResource(id = R.string.username_error)
            )

            //email
            TextFields(
                value = email,
                onValueChange = { email = it },
                label = "Email",
                color = Yellow,
                leadingIconImageVector = Icons.Default.Email,
                leadingIconDescription = "input your email",
                showError = !validateEmailRegis(email = email),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                errorMessage = stringResource(id = R.string.email_error)
            )

            //instagram
            TextFields(
                value = instagram,
                onValueChange = { instagram = it },
                label = "Instagram",
                color = Yellow,
                leadingIconImageVector = null,
                leadingIconDescription = "input your instagram account",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )

            //twitter
            TextFields(
                value = twitter,
                onValueChange = { twitter = it },
                label = "Twitter",
                color = Yellow,
                leadingIconImageVector = null,
                leadingIconDescription = "input your twitter account",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )

            //other acc
            TextFields(
                value = otherSocmed,
                onValueChange = { otherSocmed = it },
                label = "Other Social Media",
                color = Yellow,
                leadingIconImageVector = null,
                leadingIconDescription = "input your other account",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )

            //bio
            TextFields(
                value = bio,
                onValueChange = { bio = it },
                label = "Bio",
                color = Yellow,
                leadingIconImageVector = null,
                leadingIconDescription = "input bio",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )

            Spacer(
                modifier = Modifier
                    .padding(vertical = 50.dp)
            )

            FilledButton(
                text = "Save Data", color = Yellow, onClick = {},
                enable =
                if (
                    !validateDataRegis(data = name)
                    || !validateDataRegis(data = address)
                    || !validateDataRegis(data = telephoneNumber)
                    || !validateDataRegis(data = username)
                    || !validateEmailRegis(email = email)
                ) false
                else true
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            FilledButton(
                text = "Settings", color = Yellow, onClick = {},
                enable = true
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            FilledButton(
                text = "Logout", color = Yellow, onClick = {},
                enable = true
            )
//            }
        }
    }
}

@Composable
fun validateDataRegis(data: String): Boolean {
    var validateData by remember { mutableStateOf(true) }
    validateData = data.isNotBlank()
    return validateData
}

@Composable
fun validateEmailRegis(email: String): Boolean {
    var validateData by remember { mutableStateOf(true) }
    validateData = email.isNotBlank()
            && email.matches(".*@.*".toRegex())
    return validateData
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    TraleCapstoneTheme() {
        EditProfileScreen(navigateBack = { })
    }
}