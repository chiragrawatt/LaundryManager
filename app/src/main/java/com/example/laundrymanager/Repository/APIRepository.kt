package com.example.laundrymanager.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laundrymanager.Models.User
import com.example.laundrymanager.Models.UserRequest
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Services.LaundryService
import com.example.laundrymanager.Utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class APIRepository constructor(private val laundryService: LaundryService) {
    private val _userResponse = MutableLiveData<NetworkResult<UserResponse>>()

    val userResponse: LiveData<NetworkResult<UserResponse>>
    get() = _userResponse

    suspend fun signIn(userRequest: UserRequest) {
        _userResponse.postValue(NetworkResult.Loading())
        try {
            val response = laundryService.signIn(userRequest)
            if(response.isSuccessful && response.body()!=null) {
                _userResponse.postValue(NetworkResult.Success(response.body()!!))
            } else if(response.errorBody() != null) {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _userResponse.postValue(NetworkResult.Error(errorObj.getString("response")))
            } else {
                _userResponse.postValue(NetworkResult.Error("something went wrong"))
            }
        }catch(ex: Exception) {
            Log.d("testing", ex.toString())
        }
    }
}