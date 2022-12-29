package com.example.laundrymanager.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laundrymanager.Models.User
import com.example.laundrymanager.Models.UserRequest
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Services.LaundryService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class APIRepository constructor(private val laundryService: LaundryService) {
    private val _userResponse = MutableLiveData<UserResponse>()

    val userResponse: LiveData<UserResponse>
    get() = _userResponse

    suspend fun signIn(userRequest: UserRequest) {
        try {
            val result = laundryService.signIn(userRequest)
            if(result?.body() != null) {
                _userResponse.postValue(result.body())
            }
        }catch(ex: Exception) {
            Log.d("testing", ex.toString())
        }
    }
}