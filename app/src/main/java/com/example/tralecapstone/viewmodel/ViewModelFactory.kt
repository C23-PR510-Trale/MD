package com.example.tralecapstone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tralecapstone.model.PlanTripRepository

class ViewModelFactory (private val repository: PlanTripRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
//        else if (modelClass.isAssignableFrom(DetailPlanTripViewModel::class.java)) {
//            return DetailPlanTripViewModel(repository) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}