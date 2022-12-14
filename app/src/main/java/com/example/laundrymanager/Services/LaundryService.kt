package com.example.laundrymanager.Services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LaundryService {

    @GET("/quotes")
    suspend fun demoQuotes(@Query("page") page: Int) : Response<String>
}