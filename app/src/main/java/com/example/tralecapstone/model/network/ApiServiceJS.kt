package com.example.tralecapstone.model.network

import com.example.tralecapstone.model.request.EditProfile
import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.model.response.*
import retrofit2.http.*

interface ApiServiceJS {

    @POST("api/users/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : RegisterResponse

    @POST("api/users/login")
    suspend fun login(@Body loginRequest: LoginRequest) : LoginResponse

    @PATCH("api/users/update")
    suspend fun editProfile(
        @Header("Authorization") authHeader: String,
        @Body user: EditProfile) : EditProfileResponse

    @GET("api/users/trip")
    suspend fun getAllTrips() : TripsResponse

    @GET("api/users/volun")
    suspend fun getAllVoluntrips() : VoluntripResponse
//
//    @GET("stories")
//    fun getMapsStory(
//        @Query("page") page: Int?,
//        @Query("size") size: Int?,
//        @Query("location") location: Int?,
//        @Header("Authorization") authHeader: String?
//    ): Call<ResponseListStories>
//
//    @GET("stories/{id}")
//    fun getDetailStory(
//        @Path("id") id: String,
//        @Header("Authorization") authHeader: String?
//    ): Call<ResponseDetailStory>
//
//    @Multipart
//    @POST("stories")
//    fun addStory(
//        @Part photo: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Header("Authorization") authHeader: String?
//    ): Call<ResponseAddStory>
}
