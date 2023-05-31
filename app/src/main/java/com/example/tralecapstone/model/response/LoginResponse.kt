package com.example.tralecapstone.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)