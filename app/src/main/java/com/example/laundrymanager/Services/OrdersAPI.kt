package com.example.laundrymanager.Services

import retrofit2.Response
import retrofit2.http.GET

interface OrdersAPI {

    @GET("/orders")
    suspend fun getOrders() : Response<String>
}