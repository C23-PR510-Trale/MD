package com.example.tralecapstone.model

data class PlanTrip(
    val id : Int,
    val image : Int,
    val title : String,
    val price: Int,
    val rating: Double,
    val category: String,
    val openStatus: String,
    val trips: List<Trips>,
    val facilities: Facilities,
)
