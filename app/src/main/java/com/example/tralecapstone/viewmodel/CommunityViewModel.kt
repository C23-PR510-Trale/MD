package com.example.tralecapstone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tralecapstone.model.repository.PlanTripRepository
import com.example.tralecapstone.model.data.Post
import com.example.tralecapstone.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class CommunityViewModel @Inject constructor (private val repository: PlanTripRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Post>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Post>>> get() = _uiState

    fun getAllPosts() {
        viewModelScope.launch {
//            repository.getAllPosts()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { plan ->
//                    _uiState.value = UiState.Success(plan)
//                }
        }
    }
}