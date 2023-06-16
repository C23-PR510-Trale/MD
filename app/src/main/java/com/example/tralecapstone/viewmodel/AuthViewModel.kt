package com.example.tralecapstone.viewmodel

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.tralecapstone.model.network.ApiServiceJS
import com.example.tralecapstone.model.repository.AuthRepository
import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.model.response.EditProfileResponse
import com.example.tralecapstone.model.response.LoginResponse
import com.example.tralecapstone.model.response.RegisterResponse
import com.example.tralecapstone.model.response.User
import com.example.tralecapstone.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//@HiltViewModel
class AuthViewModel @Inject constructor(
//    private val api: ApiServiceJS
    private val authRepository: AuthRepository
) : ViewModel() {

    var loginInProgress = mutableStateOf(false)
    private val _login = mutableStateOf<LiveData<UiState<LoginResponse>>>(liveData { })
    val login: LiveData<UiState<LoginResponse>> get() = _login.value

    var registerInProgress = mutableStateOf(false)
    private val _register = mutableStateOf<LiveData<UiState<RegisterResponse>>>(liveData { })
    val register: LiveData<UiState<RegisterResponse>> get() = _register.value

    var editInProgress = mutableStateOf(false)
    private val _edit = mutableStateOf<LiveData<UiState<EditProfileResponse>>>(liveData { })
    val edit: LiveData<UiState<EditProfileResponse>> get() = _edit.value

    fun register(registerRequest: RegisterRequest, context: Context) {
        registerInProgress.value = true
        viewModelScope.launch {
            val result = authRepository.register(registerRequest)
            _register.value = result
            Log.d("cek register authvm", "success : ${result.value}")
            registerInProgress.value = false
        }
    }

    fun login(loginRequest: LoginRequest, context: Context) {
        loginInProgress.value = true
        viewModelScope.launch {
            val result = authRepository.login(loginRequest)
            _login.value = result
            loginInProgress.value = false
            Log.d("cek login authvm", "success : ${result.value}")
        }
    }

    fun editProfile(user: User, pass: String) {
        loginInProgress.value = true
        viewModelScope.launch {
            val result = authRepository.editProfile(user, pass)
            _edit.value = result
            editInProgress.value = false
            Log.d("cek edit authvm", "success : ${result.value}")
        }
    }
}