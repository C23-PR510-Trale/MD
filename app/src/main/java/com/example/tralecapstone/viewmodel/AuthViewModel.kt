package com.example.tralecapstone.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.tralecapstone.model.repository.AuthRepository
import com.example.tralecapstone.model.request.EditProfile
import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.model.response.EditProfileResponse
import com.example.tralecapstone.model.response.LoginResponse
import com.example.tralecapstone.model.response.RegisterResponse
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
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

    fun register(registerRequest: RegisterRequest) {
        registerInProgress.value = true
        viewModelScope.launch {
            val result = authRepository.register(registerRequest)
            _register.value = result
            Log.d("cek register authvm", "success : ${result.value}")
            registerInProgress.value = false
        }
    }

    fun login(loginRequest: LoginRequest) {
        loginInProgress.value = true
        viewModelScope.launch {
            val result = authRepository.login(loginRequest)
            _login.value = result
            loginInProgress.value = false
            Log.d("cek login authvm", "success : ${result.value}")
        }
    }

    fun editProfile(user: EditProfile, token: String) {
        loginInProgress.value = true
        viewModelScope.launch {
            val result = authRepository.editProfile(user, token)
            _edit.value = result
            editInProgress.value = false
            Log.d("cek edit authvm", "success : ${result.value}")
        }
    }
}