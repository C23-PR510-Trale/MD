package com.example.tralecapstone.di

import com.example.tralecapstone.model.PlanTripRepository

object Injection {
    fun provideRepository(): PlanTripRepository {
        return PlanTripRepository.getInstance()
    }
}