package com.example.tralecapstone.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name")
    var name: String,
    @SerializedName("address")
    var address: String,
    @SerializedName("telephoneNumber")
    var telephoneNumber: String,
    @SerializedName("username")
    var username: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String
)
