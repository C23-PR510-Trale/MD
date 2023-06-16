package com.example.tralecapstone.model.response

data class Prediction(
    val budget: List<Double>,
    val location: List<String>,
    val rating: List<Double>,
    val trip_id: List<Int>,
    val trip_name: List<String>,
)