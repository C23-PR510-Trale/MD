package com.example.tralecapstone.model.network

import com.example.tralecapstone.model.request.AddPlanRequest
import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.request.RegisterRequest
import com.example.tralecapstone.model.response.AddPlanResponse
import com.example.tralecapstone.model.response.LoginResponse
import com.example.tralecapstone.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServicePython {
    @POST("/")
    suspend fun findPlan(@Body addPlanRequest: AddPlanRequest) : AddPlanResponse

//    @GET("stories")
//    suspend fun getAllStories(
//        @Query("page") page: Int?,
//        @Query("size") size: Int?,
//        @Query("location") location: Int?,
//        @Header("Authorization") authHeader: String?
//    ): ResponseListStories

//    @Multipart
//    @POST("stories")
//    fun addStory(
//        @Part photo: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Header("Authorization") authHeader: String?
//    ): Call<ResponseAddStory>
}
