package com.example.tralecapstone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.Orange300
import com.example.tralecapstone.ui.theme.Orange400
import com.example.tralecapstone.ui.theme.Yellow100

@Composable
fun Register() {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var isPassVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Yellow100),
    ) {
        TextFields(
            value = username,
            onValueChange = {username = it},
            label = "Username",
            leadingIconImageVector = Icons.Default.AccountCircle,
            leadingIconDescription = "input your username",
            showError = !validateUsernameRegis(username = username),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            errorMessage = stringResource(id = R.string.username_error)
        )

        TextFields(
            value = password,
            onValueChange = {password = it},
            label = "Password",
            leadingIconImageVector = Icons.Default.AccountCircle,
            leadingIconDescription = "input your password",
            showError = !validatePasswordRegis(password = password),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            errorMessage = stringResource(id = R.string.password_error)
        )

        //kasi spacer

        FilledButton(text = "Register", color = Color.White, onClick = {},
            enable =  if(!validateUsernameRegis(username = username) && !validatePasswordRegis(password = password)) false else true)
    }
}

@Composable
fun validateUsernameRegis(username : String) : Boolean {
    var validateUsername by remember { mutableStateOf(true) }
    validateUsername = username.isNotBlank()
    return validateUsername
}

@Composable
fun validatePasswordRegis(password : String) : Boolean {
    var validatePassword by remember { mutableStateOf(true) }

    validatePassword =
        password.length > 8
                && !password.matches(".*[A-Z].*".toRegex())
                && !password.matches(".*[a-z].*".toRegex())
                && !password.matches(".*[#@%*&_-].*".toRegex())

    return validatePassword
}
