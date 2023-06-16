package com.example.tralecapstone.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.tralecapstone.model.repository.PlanTripRepository
import com.example.tralecapstone.model.request.AddPlanRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.model.response.AddPlanResponse
import com.example.tralecapstone.model.response.LoginResponse
import com.example.tralecapstone.model.response.RegisterResponse
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class ListPlanViewModel @Inject constructor (private val repository: PlanTripRepository) : ViewModel() {

    var listPlanProgress = mutableStateOf(false)
//    private var _listPlan :MutableStateFlow<UiState<List<AddPlanResponse>>> = MutableStateFlow(UiState.Loading)
//    val listPlan: StateFlow<UiState<List<AddPlanResponse>>> get() = _listPlan

//    private val _listPlan: MutableStateFlow<UiState<List<AddPlanResponse>>> = MutableStateFlow(UiState.Loading)
//    val listPlan: StateFlow<UiState<List<AddPlanResponse>>> get() = _listPlan

    private val _listPlan: MutableStateFlow<UiState<AddPlanResponse>> = MutableStateFlow(UiState.Loading)
    val listPlan: StateFlow<UiState<AddPlanResponse>> get() = _listPlan

    fun getAllList(addPlanRequest: AddPlanRequest) {
        listPlanProgress.value = true
        viewModelScope.launch {
//            repository.findPlan(addPlanRequest).catch {
//                    _listPlan.value = UiState.Error(it.message.toString())
//                }
//                .collect { plan ->
//                    _listPlan.value = UiState.Success(plan)
//                }

//            val result = repository.findPlan(addPlanRequest)
//            _listPlan.value = result
//            listPlanProgress.value = false
            repository.findPlan(addPlanRequest)
                .catch {
                    _listPlan.value = UiState.Error(it.message.toString())
                    Log.d("cek getAllList vm", "error : ${it.message.toString()}")
                }
                .collect { plans ->
                    _listPlan.value = UiState.Success(plans)
                    Log.d("cek getAllList vm", "success : ${_listPlan.value}")
                }

//            try {
//                val listplan = repository.findPlan(addPlanRequest)
//                _listPlan.value = listplan
//            } catch (e: Exception) {
////                emit(UiState.Error(e.message.toString()))
//                Log.d("cek getAllList vm", "exception : ${e.message} ")
//            }
        }
    }
}