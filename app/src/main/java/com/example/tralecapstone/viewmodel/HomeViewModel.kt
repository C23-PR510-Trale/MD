package com.example.tralecapstone.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tralecapstone.model.Data
import com.example.tralecapstone.model.PlanTrip
import com.example.tralecapstone.model.PlanTripRepository
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: PlanTripRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Data>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Data>>> get() = _uiState

    private val _plantrips = MutableStateFlow(repository.getResultTrips()
        .sortedBy { it.title }
        .groupBy { it.title[0] }
    )
    val plantrips: StateFlow<Map<Char, List<PlanTrip>>> get() = _plantrips

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _plantrips.value = repository.searchTrip(_query.value)
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    }

    fun getAllPlanTrips() {
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