package com.example.tralecapstone.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HistoryViewModel (private val repository: PlanTripRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<PlanTrip>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<PlanTrip>>> get() = _uiState

    fun getAllHistory() {
        viewModelScope.launch {
            repository.getAllPlans()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { plan ->
                    _uiState.value = UiState.Success(plan)
                }
        }
    }
}