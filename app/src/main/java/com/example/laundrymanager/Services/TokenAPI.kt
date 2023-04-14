package com.example.laundrymanager.Services

import com.example.laundrymanager.Models.MessageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface TokenAPI {
    @GET("/user/token")
    suspend fun checkToken() : Response<MessageResponse>
}