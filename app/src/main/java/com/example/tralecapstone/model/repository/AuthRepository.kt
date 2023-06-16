package com.example.tralecapstone.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tralecapstone.datastore.DataPreferences
import com.example.tralecapstone.model.network.ApiServiceJS
import com.example.tralecapstone.model.request.EditProfile
import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.model.response.*
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AuthRepository(
    private val pref: DataPreferences,
    private val apiService: ApiServiceJS,
) {
    fun register(registerRequest: RegisterRequest): LiveData<UiState<RegisterResponse>> = liveData {
        try {
            val response = apiService.register(registerRequest)
            if (response.success == 1) {
                emit(UiState.Success(response))
                Log.d("regis authrepo", "success = ${response.success} : ${response.message} ")
            }
            else {
                emit(UiState.Error(response.message))
                Log.d("regis authrepo", "success = ${response.success} : ${response.data.message} ")
            }
        } catch (e: Exception) {
            emit(UiState.Error(e.message.toString()))
            Log.d("regis authrepo", "exception : ${e.message} ")
        }
    }

    fun login(loginRequest: LoginRequest): LiveData<UiState<LoginResponse>> = liveData {
        emit(UiState.Loading)
        try {
            val response = apiService.login(loginRequest)
            Log.d("login authrepo", "success: ${response.success} ${response.message} ${response.data.id}")
            if (response.success == 1) {
                emit(UiState.Success(response))
                pref.saveSession(ids = response.data.id, names = response.data.nama, emails = response.data.email, addresses = response.data.address, telps = response.data.telp, tokens = response.token, bios = response.data.bio, passwords = loginRequest.password)
                Log.d("login authrepo", "success = ${response.success} : ${response.token} ")
            } else {
                emit(UiState.Error(response.message))
                Log.d("login authrepo", "success = ${response.success} : ${response.message} ${response.data.id} ")
            }
        } catch (e: Exception) {
            Log.d("login authrepo", "exception: ${e.message.toString()} ")
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun editProfile(user: EditProfile, token:String): LiveData<UiState<EditProfileResponse>> = liveData {
        emit(UiState.Loading)
        try {
            val response = apiService.editProfile(token, user)
            Log.d("editprof authrepo", "success: ${response.success} ${response.message}")
            if (response.success == 1) {
                emit(UiState.Success(response))
                Log.d("editprof authrepo", "success = ${response.success} : ${response.message}")
            } else {
                emit(UiState.Error(response.message))
                Log.d("editprof authrepo", "success = ${response.success} : ${response.message}")
            }
        } catch (e: Exception) {
            Log.d("editprof authrepo", "exception: ${e.message.toString()} ")
            emit(UiState.Error(e.message.toString()))
        }
    }

    suspend fun getAllTrips(): Flow<TripsResponse> {
        val response = apiService.getAllTrips()
        return flowOf(response)
    }

    suspend fun getAllVoluntrip(): Flow<VoluntripResponse> {
        val response = apiService.getAllVoluntrips()
        return flowOf(response)
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            preferences: DataPreferences,
            apiService: ApiServiceJS
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(preferences, apiService)
            }.also { instance = it }
    }
}