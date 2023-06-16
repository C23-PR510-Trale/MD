package com.example.tralecapstone.model.network

import com.example.tralecapstone.model.request.AddPlanRequest
import com.example.tralecapstone.model.response.AddPlanResponse
import retrofit2.http.*

interface ApiServicePython {
    @POST("/")
    suspend fun findPlan(@Body addPlanRequest: AddPlanRequest) : AddPlanResponse
}
