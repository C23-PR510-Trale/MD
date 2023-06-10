package com.example.tralecapstone.model

import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.model.response.LoginResponse
import com.example.tralecapstone.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServiceJS {
    @POST("/register")
    fun register(@Body registerRequest: RegisterRequest) : Call<RegisterResponse>
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest) : Call<LoginResponse>

//    @FormUrlEncoded
//    @POST("login")
//    fun login(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<ResponseLogin>
//
//    @GET("stories")
//    suspend fun getAllStories(
//        @Query("page") page: Int?,
//        @Query("size") size: Int?,
//        @Query("location") location: Int?,
//        @Header("Authorization") authHeader: String?
//    ): ResponseListStories
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
