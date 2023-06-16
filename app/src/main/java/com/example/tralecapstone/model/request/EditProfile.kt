package com.example.tralecapstone.model.request

import com.google.gson.annotations.SerializedName

data class EditProfile(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("telp")
    val telp: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("bio")
    val bio: String,
)