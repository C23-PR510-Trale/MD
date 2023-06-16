package com.example.tralecapstone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tralecapstone.model.repository.PlanTripRepository

class ViewModelFactory (private val repository: PlanTripRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(ListPlanViewModel::class.java)) {
            return ListPlanViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(CommunityViewModel::class.java)) {
            return CommunityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}