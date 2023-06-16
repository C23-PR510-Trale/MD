package com.example.tralecapstone.ui.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Mail
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tralecapstone.R
import com.example.tralecapstone.datastore.DataPreferences
import com.example.tralecapstone.datastore.PreferenceViewModel
import com.example.tralecapstone.datastore.ViewModelFactoryDataStore
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.ui.screen.validateEmailRegis
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.viewmodel.AuthViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactoryAuth

const val DATASTORE = "user"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)

@Composable
fun Login(
    authVM: AuthViewModel = viewModel(
        factory = ViewModelFactoryAuth(Injection.provideRepositoryAuth(LocalContext.current.dataStore))
    ),
) : Boolean {

    val pref = DataPreferences.getInstance(LocalContext.current.dataStore)
    val prefVM: PreferenceViewModel = viewModel(
        factory = ViewModelFactoryDataStore(pref)
    )

    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp),
    ) {
        TextFields(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            color = Yellow,
            leadingIconImageVector = Icons.Rounded.Mail,
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

        TextFields(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            color = Yellow,
            leadingIconImageVector = Icons.Rounded.Lock,
            leadingIconDescription = "input your password",
            showError = !validatePasswordLogin(password = password),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            errorMessage = stringResource(id = R.string.password_error)
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 50.dp)
        )

        val context = LocalContext.current

        FilledButton(
            text = "Login", color = Yellow,
            onClick = {
                authVM.login(LoginRequest(email, password))
                Log.d(
                    "cek login click",
                    authVM.login(LoginRequest(email, password)).toString()
                )
            },
            enable =
            !(!validateEmailRegis(email = email) || !validatePasswordLogin(password = password))
        )

        val loginState by authVM.login.observeAsState()
        loginState?.let { result ->
            when (result) {
                is UiState.Success -> {
                    Log.d(
                        "cek login uisuccess",
                        "success : ${result.data.success} = ${result.data.message}"
                    )
                prefVM.saveSession(
                    id = result.data.data.id,
                    name = result.data.data.nama,
                    email = result.data.data.email,
                    address = result.data.data.address,
                    telp = result.data.data.telp,
                    token = result.data.token,
                    bio = result.data.data.bio,
                    password = password
                )
                    status = true
                }

                is UiState.Error -> {
                    Log.d("cek login uierror", "error : ${result.errorMessage}")
                    Toast.makeText(context, "Error: ${result.errorMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
                is UiState.Loading -> {
                    Log.d("cek login uiloading", result.toString())
                }
            }
        }
    }
    return status
}

@Composable
fun validatePasswordLogin(password: String): Boolean {
    var validatePassword by remember { mutableStateOf(true) }

    validatePassword =
        password.length >= 8
                && password.matches(".*[A-Z].*".toRegex())
                && password.matches(".*[a-z].*".toRegex())

    return validatePassword
}
