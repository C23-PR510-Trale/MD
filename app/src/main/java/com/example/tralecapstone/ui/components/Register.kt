package com.example.tralecapstone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tralecapstone.R
import com.example.tralecapstone.ui.theme.Orange300
import com.example.tralecapstone.ui.theme.Orange400
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.ui.theme.Yellow100

@Composable
fun Register() {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var name by remember{ mutableStateOf("") }
    var address by remember{ mutableStateOf("") }
    var telephoneNumber by remember{ mutableStateOf("") }
    var username by remember{ mutableStateOf("") }
    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var confirmPassword by remember{ mutableStateOf("") }
    var isPassVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .verticalScroll(state = rememberScrollState(), enabled = true)
            .padding(10.dp),
    ) {

        //Name
        TextFields(
            value = name,
            onValueChange = {name = it},
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
            onValueChange = {address = it},
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
            onValueChange = {telephoneNumber = it},
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
            onValueChange = {username = it},
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
            onValueChange = {email = it},
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

        //password
        TextFields(
            value = password,
            onValueChange = {password = it},
            label = "Password",
            color = Yellow,
            leadingIconImageVector = Icons.Default.Lock,
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

        //confirmation password
        TextFields(
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            label = "Confirm Password",
            color = Yellow,
            leadingIconImageVector = Icons.Default.Lock,
            leadingIconDescription = "confirm your password",
            showError = !validatePasswordRegis(password = confirmPassword) || password == confirmPassword,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            errorMessage = stringResource(id = R.string.confpass_error)
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 50.dp)
        )

        FilledButton(
            text = "Register", color = Yellow, onClick = {},
            enable =
            if(
                !validateDataRegis(data = name)
                || !validateDataRegis(data = address)
                || !validateDataRegis(data = telephoneNumber)
                || !validateDataRegis(data = username)
                || !validateEmailRegis(email = email)
                || !validatePasswordRegis(password = password)
                || !validatePasswordRegis(password = confirmPassword)
                || password == confirmPassword
            ) false
            else true
        )
    }
}

@Composable
fun validateDataRegis(data : String) : Boolean {
    var validateData by remember { mutableStateOf(true) }
    validateData = data.isNotBlank()
    return validateData
}

@Composable
fun validateEmailRegis(email : String) : Boolean {
    var validateData by remember { mutableStateOf(true) }
    validateData = email.isNotBlank()
            && email.matches(".*@.*".toRegex())
    return validateData
}

@Composable
fun validatePasswordRegis(password : String) : Boolean {
    var validatePassword by remember { mutableStateOf(true) }

    validatePassword =
        password.length > 8
                && password.matches(".*[A-Z].*".toRegex())
                && password.matches(".*[a-z].*".toRegex())
                && password.matches(".*[#@%*&_-].*".toRegex())

    return validatePassword
}
