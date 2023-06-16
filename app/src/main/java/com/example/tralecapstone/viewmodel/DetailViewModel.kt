package com.example.tralecapstone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tralecapstone.model.repository.PlanTripRepository
import com.example.tralecapstone.model.response.Prediction
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class DetailViewModel @Inject constructor (private val repository: PlanTripRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Prediction>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Prediction>> get() = _uiState

    fun getPlanById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
//            _uiState.value = UiState.Success(repository.getPlansById(id))
        }
    }
}