package com.example.tralecapstone.ui.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tralecapstone.R
import com.example.tralecapstone.datastore.DataPreferences
import com.example.tralecapstone.datastore.PreferenceViewModel
import com.example.tralecapstone.datastore.ViewModelFactoryDataStore
import com.example.tralecapstone.di.Injection
import com.example.tralecapstone.model.request.EditProfile
import com.example.tralecapstone.model.response.User
import com.example.tralecapstone.ui.components.DATASTORE
import com.example.tralecapstone.ui.components.FilledButton
import com.example.tralecapstone.ui.components.TextFields
import com.example.tralecapstone.ui.state.UiState
import com.example.tralecapstone.ui.theme.TraleCapstoneTheme
import com.example.tralecapstone.ui.theme.Yellow
import com.example.tralecapstone.viewmodel.AuthViewModel
import com.example.tralecapstone.viewmodel.ViewModelFactoryAuth

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE)

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

            Text(
                text = "Your Profile",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
                    .padding(horizontal = 10.dp, vertical = 16.dp)
            )

            val context = LocalContext.current
            val focusManager = LocalFocusManager.current
            val scrollState = rememberScrollState()

            val pref = DataPreferences.getInstance(LocalContext.current.dataStore)
            val prefVM: PreferenceViewModel = viewModel(
                factory = ViewModelFactoryDataStore(pref)
            )

//            val nama = prefVM.getName().collectAsState(initial = "").value
//            val token by viewModel.getToken().observeAsState()

            var error by remember { mutableStateOf(false) }

            var idUser by remember { mutableStateOf(0) }
            var token by remember { mutableStateOf("") }
            var passwd by remember { mutableStateOf("") }

            val id by prefVM.getId().observeAsState()
            id?.let {
                idUser = it
            }
            val tokens by prefVM.getToken().observeAsState()
            tokens?.let {
                token = it
            }
            val pass by prefVM.getPassword().observeAsState()
            pass?.let {
                passwd = it
            }

            //Name
            val nama by prefVM.getName().observeAsState()
            var namaa by remember { mutableStateOf("") }
            nama?.let {
                var name by remember { mutableStateOf(it) }
//                name = it
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
                error = !validateDataRegis(data = name)
                namaa = name
            }

//address
            val addr by prefVM.getAddress().observeAsState()
            var addrss by remember { mutableStateOf("") }
            addr?.let {
                var address by remember { mutableStateOf(it) }
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
                error = !validateDataRegis(data = address)
                addrss = address
            }

            //telephone number
            val telp by prefVM.getTelp().observeAsState()
            var telpp by remember { mutableStateOf("") }
            telp?.let {
                var telephoneNumber by remember { mutableStateOf(it) }
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
                error = !validateDataRegis(data = telephoneNumber)
                telpp = telephoneNumber
            }

            //email
            val mail by prefVM.getEmail().observeAsState()
            var maill by remember { mutableStateOf("") }
            mail?.let {
                var email by remember { mutableStateOf(it) }
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
                error = !validateEmailRegis(email = email)
                maill = email
            }

            val bios by prefVM.getBio().observeAsState()
            var bioo by remember { mutableStateOf("") }
            bios?.let {
                var bio by remember { mutableStateOf(it) }
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
                bioo = bio
            }

            Spacer(
                modifier = Modifier
                    .padding(vertical = 50.dp)
            )

            val authVM: AuthViewModel = viewModel(
                factory = ViewModelFactoryAuth(Injection.provideRepositoryAuth(LocalContext.current.dataStore))
            )

            FilledButton(
                text = "Save Data", color = Yellow, onClick = {
                    prefVM.saveSession(
                        idUser,
                        namaa,
                        addrss,
                        maill,
                        telpp,
                        bioo,
                        token,
                        passwd
                    )
                    authVM.editProfile(
                        EditProfile(
                            id = idUser,
                            nama = namaa,
                            address = addrss,
                            email = maill,
                            password = passwd,
                            telp = telpp,
                            bio = bioo
                        ), "bearer $token"
                    )

//                    Toast.makeText(
//                        context,
//                        "$name ; $address ; $telephoneNumber ; $email ; $bio",
//                        Toast.LENGTH_SHORT
//                    ).show()
                },
                enable = if (error) false else true
            )

            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            FilledButton(
                text = "Logout", color = Yellow, onClick = {
                    prefVM.clearSession()
                    navigateBack()
                },
                enable = true
            )

            Spacer(modifier = Modifier.padding(vertical = 24.dp))

            val editState by authVM.edit.observeAsState()
            editState?.let { result ->
                when (result) {
                    is UiState.Success -> {
                        Log.d(
                            "cek editprof uisuccess",
                            "success : ${result.data.success} = ${result.data.message}"
                        )
                        Toast.makeText(context, "Edit Data Success", Toast.LENGTH_SHORT).show()
                    }

                    is UiState.Error -> {
//                            val errorMessage = result.data
//                            println(errorMessage)
//                            loginResult?.let {
//                                Text(text = it.toString())
//                                Text(text = errorMessage)
//                            }
                        Log.d("cek editprof uierror", "error : ${result.errorMessage}")
                        Toast.makeText(context, "Error: ${result.errorMessage}", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is UiState.Loading -> {
                        Log.d("cek editprof uiloading", result.toString())
                    }
                }
            }
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