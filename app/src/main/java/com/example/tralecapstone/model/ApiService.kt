package com.example.tralecapstone.model

import com.example.tralecapstone.model.request.LoginRequest
import com.example.tralecapstone.model.response.LoginResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
//    @POST("/")
//    fun register(@Body loginRequest: LoginRequest) : Call<ResponseLogin>
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
