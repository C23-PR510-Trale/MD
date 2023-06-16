package com.example.tralecapstone.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.tralecapstone.datastore.DataPreferences
import com.example.tralecapstone.model.data.*
import com.example.tralecapstone.model.network.ApiServicePython
import com.example.tralecapstone.model.request.AddPlanRequest
import com.example.tralecapstone.model.response.AddPlanResponse
import com.example.tralecapstone.model.response.TripsResponse
import com.example.tralecapstone.ui.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanTripRepository(
    private val pref: DataPreferences,
    private val apiService: ApiServicePython,
) {
    suspend fun findPlan(addPlanRequest: AddPlanRequest): Flow<AddPlanResponse> {
        val response = apiService.findPlan(addPlanRequest)
        return flowOf(response)
    }

//    fun getAllPlans(): Flow<List<AddPlanResponse>> {
//        return flowOf(datas)
//    }
//
//    fun getAllPosts(): Flow<List<Post>> {
//        return flowOf(posts)
//    }
//
//    fun getAllVoluntrip(): Flow<List<Voluntrip>> {
//        return flowOf(voluntrips)
//    }
//
//    fun getResultTrips(): List<PlanTrip> {
//        return plans
//    }
//
//    fun searchTrip(query: String): List<PlanTrip> {
//        return plans.filter {
//            it.title.contains(query, ignoreCase = true)
//        }
//    }
//
//    fun getPlansById(id: Int): Data {
//        return datas.first {
//            it.planTrip.id == id
//        }
//    }

    companion object {
        @Volatile
        private var instance: PlanTripRepository? = null

        fun getInstance(
            preferences: DataPreferences,
            apiService: ApiServicePython
        ): PlanTripRepository =
            instance ?: synchronized(this) {
                PlanTripRepository(preferences, apiService).apply {
                    instance = this
                }
            }
    }
}