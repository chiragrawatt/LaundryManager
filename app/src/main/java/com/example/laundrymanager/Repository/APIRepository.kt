package com.example.laundrymanager.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laundrymanager.Models.User
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Services.LaundryService
import retrofit2.Response

class APIRepository(private val laundryService: LaundryService) {

//    private val _msg = MutableLiveData<String>("ello its me gmd")
//
//    val msg: LiveData<String>
//    get() = _msg


    suspend fun signIn(email: String, password: String) : UserResponse? {
        return try {
            val result = laundryService.signIn(User("", email, "", password, 0, "", 0))
            UserResponse(result.body()!!.response, result.body()!!.user)
        }catch(ex: Exception) {
            Log.d("testing", ex.toString())
            null
        }
    }

//    suspend fun testURL() {
//        val result: Response<String>
//        try {
//            result = laundryService.demoQuotes()
//            if(result.body() != null) {
//                Log.d("testing", "got it")
//                _msg.postValue(result.body())
//            }
//        }catch(ex: Exception) {
//            _msg.postValue("nil")
//            Log.d("testing", ex.toString())
//        }
//    }
}