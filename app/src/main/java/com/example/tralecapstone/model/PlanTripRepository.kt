package com.example.tralecapstone.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class PlanTripRepository {
    private val plans = mutableListOf<PlanTrip>()
    private val posts = mutableListOf<Post>()
    private val voluntrips = mutableListOf<Voluntrip>()

    init {
        if (plans.isEmpty()) {
//            FakeRewardDataSource.dummyRewards.forEach {
//                orderRewards.add(OrderReward(it, 0))
//            }
        }
    }

    fun getAllPlans(): Flow<List<PlanTrip>> {
        return flowOf(plans)
    }

    fun getAllPosts(): Flow<List<Post>> {
        return flowOf(posts)
    }

    fun getAllVoluntrip(): Flow<List<Voluntrip>> {
        return flowOf(voluntrips)
    }

    fun getResultTrips(): List<PlanTrip> {
        return plans
    }

    fun searchTrip(query: String): List<PlanTrip>{
        return plans.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }

    fun getPlansById(id: Int): PlanTrip {
        return plans.first {
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance: PlanTripRepository? = null

        fun getInstance(): PlanTripRepository =
            instance ?: synchronized(this) {
                PlanTripRepository().apply {
                    instance = this
                }
            }
    }
}