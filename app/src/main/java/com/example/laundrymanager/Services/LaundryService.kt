package com.example.laundrymanager.Services

import com.example.laundrymanager.Models.User
import com.example.laundrymanager.Models.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface LaundryService {

//    @GET("/")
//    suspend fun demoQuotes() : Response<String>

    @POST("/user/signin")
    suspend fun signIn(@Body userData: User) : Response<UserResponse>
}