package com.example.tralecapstone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tralecapstone.model.Data
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: PlanTripRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Data>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Data>> get() = _uiState

    fun getPlanById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getPlansById(id))
        }
    }
}