package com.example.tralecapstone.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("telp")
    val telp: String
)