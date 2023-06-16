package com.example.tralecapstone.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tralecapstone.model.repository.AuthRepository
import com.example.tralecapstone.model.response.TripsResponse
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor (private val repository: AuthRepository) : ViewModel() {
    private val _listPlan: MutableStateFlow<UiState<TripsResponse>> = MutableStateFlow(UiState.Loading)
    val listPlan: StateFlow<UiState<TripsResponse>> get() = _listPlan

    fun getAllPlanTrips() {
        viewModelScope.launch {
            repository.getAllTrips()
                .catch {
                    _listPlan.value = UiState.Error(it.message.toString())
                    Log.d("cek getAllList vm", "error : ${it.message.toString()}")
                }
                .collect { plans ->
                    _listPlan.value = UiState.Success(plans)
                    Log.d("cek getAllList vm", "success : ${_listPlan.value}")
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
//        _plantrips.value = repository.searchTrip(_query.value)
//            .sortedBy { it.title }
//            .groupBy { it.title[0] }
    }

//    fun getAllPlanTrips() {
//        viewModelScope.launch {
//            repository.getAllPlans()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { plan ->
//                    _uiState.value = UiState.Success(plan)
//                }
//        }
//    }
}