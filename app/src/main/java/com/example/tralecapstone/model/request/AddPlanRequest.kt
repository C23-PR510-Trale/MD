package com.example.tralecapstone.model.request

import com.google.gson.annotations.SerializedName

data class AddPlanRequest(
    @SerializedName("category")
    val category: String,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("num_of_recom")
    val num_of_recom: Int
)