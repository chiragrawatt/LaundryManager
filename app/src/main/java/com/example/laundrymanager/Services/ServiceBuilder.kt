package com.example.laundrymanager.Services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val URL = "https://quo"
    //private val okHTTP = OkHttpClient.Builder()

    fun getInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}