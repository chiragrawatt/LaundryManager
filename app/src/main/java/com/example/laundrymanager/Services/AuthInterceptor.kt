package com.example.laundrymanager.Services

import androidx.fragment.app.activityViewModels
import com.example.laundrymanager.Repository.DataStoreRepository
import com.example.laundrymanager.Utils.TokenManager
import com.example.laundrymanager.ViewModels.SessionViewModel
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor : Interceptor {

    @Inject lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = tokenManager.getToken()
        request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request.build())
    }
}