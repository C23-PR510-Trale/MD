package com.example.tralecapstone.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.tralecapstone.datastore.DataPreferences
import com.example.tralecapstone.model.network.ApiConfigJS
import com.example.tralecapstone.model.network.ApiConfigPython
import com.example.tralecapstone.model.repository.AuthRepository
import com.example.tralecapstone.model.repository.PlanTripRepository

object Injection {
    fun provideRepositoryAuth(dataStore: DataStore<Preferences>): AuthRepository {
        val preferences = DataPreferences(dataStore)
        val apiService = ApiConfigJS.getApiService()
        return AuthRepository.getInstance(preferences, apiService)
    }
    fun provideRepositoryPlanTrip(): PlanTripRepository {
        val apiService = ApiConfigPython.getApiService()
        return PlanTripRepository.getInstance(apiService)
    }
}