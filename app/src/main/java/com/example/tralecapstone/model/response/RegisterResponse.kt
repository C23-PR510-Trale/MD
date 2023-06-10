package com.example.tralecapstone.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: User
)