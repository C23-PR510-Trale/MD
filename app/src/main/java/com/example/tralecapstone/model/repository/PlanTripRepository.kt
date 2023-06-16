package com.example.tralecapstone.model.repository

import com.example.tralecapstone.model.network.ApiServicePython
import com.example.tralecapstone.model.request.AddPlanRequest
import com.example.tralecapstone.model.response.AddPlanResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PlanTripRepository(
    private val apiService: ApiServicePython,
) {
    suspend fun findPlan(addPlanRequest: AddPlanRequest): Flow<AddPlanResponse> {
        val response = apiService.findPlan(addPlanRequest)
        return flowOf(response)
    }

    companion object {
        @Volatile
        private var instance: PlanTripRepository? = null

        fun getInstance(
            apiService: ApiServicePython
        ): PlanTripRepository =
            instance ?: synchronized(this) {
                PlanTripRepository(apiService).apply {
                    instance = this
                }
            }
    }
}