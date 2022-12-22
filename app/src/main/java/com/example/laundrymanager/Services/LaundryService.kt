package com.example.laundrymanager.Services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LaundryService {

    @GET("/")
    suspend fun demoQuotes() : Response<String>
}