package com.example.tralecapstone.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tralecapstone.model.repository.AuthRepository
import com.example.tralecapstone.model.response.VoluntripResponse
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class VoluntripViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<VoluntripResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<VoluntripResponse>> get() = _uiState

    fun getAllVoluntrip() {
        viewModelScope.launch {
            repository.getAllVoluntrip()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                    Log.d("cek getAllVol vm", "error : ${it.message.toString()}")
                }
                .collect { plans ->
                    _uiState.value = UiState.Success(plans)
                    Log.d("cek getAllVol vm", "success : ${_uiState.value}")
                }
        }
    }
}