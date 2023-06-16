package com.example.tralecapstone.ui.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tralecapstone.R
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.ui.navigation.Screen
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.viewmodel.AuthViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactory
import com.example.tralecapstone.viewmodel.ViewModelFactoryAuth

@Composable
fun Register(
//    onCLick : () -> Unit,
//    viewModel: AuthViewModel = hiltViewModel(),
    authVM: AuthViewModel = viewModel(
        factory = ViewModelFactoryAuth(Injection.provideRepositoryAuth(LocalContext.current.dataStore))
    ),
) {

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var telephoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
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

        //password
        TextFields(
            value = password,
            onValueChange = { password = it },
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
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            color = Yellow,
            leadingIconImageVector = Icons.Default.Lock,
            leadingIconDescription = "confirm your password",
            showError = !validatePasswordRegis(password = confirmPassword) || !password.equals(confirmPassword),
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

//        val authVM = hiltViewModel<AuthViewModel>()
        val context = LocalContext.current
        val registerState by authVM.register.observeAsState()

        FilledButton(
            text = "Register", color = Yellow,
            onClick = {
                authVM.register(
                    RegisterRequest(
                        email = email,
                        password = password,
                        nama = name,
                        address = address,
                        telp = telephoneNumber,
                        bio = ""
                    ), context
                )
                Log.d("cek register click",
                    authVM.register(
                        RegisterRequest(
                            email = email,
                            password = password,
                            nama = name,
                            address = address,
                            telp = telephoneNumber,
                            bio = ""
                        ), context
                    ).toString()
                )
            },
            enable =
            if (
                !validateDataRegis(data = name)
                || !validateDataRegis(data = address)
                || !validateDataRegis(data = telephoneNumber)
                || !validateEmailRegis(email = email)
                || !validatePasswordRegis(password = password)
                || !validatePasswordRegis(password = confirmPassword)
                || !password.equals(confirmPassword)
            ) false
            else true
        )

        registerState?.let { result ->
            when (result) {
                is UiState.Success -> {
//                    LoginViewModel.saveToken(context, result.data)
                    Log.d(
                        "cek register uisuccess",
                        "success : ${result.data.success} = ${result.data.message}"
                    )
                    Toast.makeText(context, "Register Success! Let's Login to Continue", Toast.LENGTH_SHORT).show()
//                    {
//                        popUpTo(navController.graph.startDestinationId)
//                        launchSingleTop = true
//                    }
                }

                is UiState.Error -> {
//                            val errorMessage = result.data
//                            println(errorMessage)
//                            loginResult?.let {
//                                Text(text = it.toString())
//                                Text(text = errorMessage)
//                            }
                    Log.d("cek register uierror", "error : ${result.errorMessage}")
                    Toast.makeText(context, "Error ${result.errorMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
                is UiState.Loading -> {
                    Log.d("cek register uiloading", result.toString())
                }
            }
        }

//        authVM.register.collectAsState(initial = UiState.Loading).value.let { uiState ->
//            when (uiState) {
//                is UiState.Loading -> {}
//                is UiState.Success -> {
//                    val data = uiState.data
//                    Log.d("cek authvm register", uiState.data.success.toString())
//                    if (data.success == 1) Toast.makeText(
//                        context,
//                        "1 ${data.data.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    else if (data.success == 0) Toast.makeText(
//                        context,
//                        "0 ${data.data.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    else Toast.makeText(
//                        context,
//                        "${data.success} ${data.data.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    }
//                is UiState.Error -> {}
//            }
//        }
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

@Composable
fun validatePasswordRegis(password: String): Boolean {
    var validatePassword by remember { mutableStateOf(true) }

    validatePassword =
        password.length >= 8
                && password.matches(".*[A-Z].*".toRegex())
                && password.matches(".*[a-z].*".toRegex())
                && password.matches(".*[#@%*&_-].*".toRegex())

    return validatePassword
}
