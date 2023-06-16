package com.example.tralecapstone.model.response

data class DataTrips(
    val budget: Int,
    val category: String,
    val id: Int,
    val location: String,
    val rating: Double,
    val trip_name: String
)