package com.example.laundrymanager.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laundrymanager.Models.UserRequest
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Services.UserAPI
import com.example.laundrymanager.Utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response

class APIRepository constructor(private val userAPI: UserAPI) {
    private val _userResponse = MutableLiveData<NetworkResult<UserResponse>>()

    val userResponse: LiveData<NetworkResult<UserResponse>>
    get() = _userResponse

    suspend fun signIn(userRequest: UserRequest) {
        _userResponse.postValue(NetworkResult.Loading())
        try {
            val response = userAPI.signIn(userRequest)
            handleResponse(response)
        }catch(ex: Exception) {
            Log.d("testing", ex.toString())
        }
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponse.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponse.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userResponse.postValue(NetworkResult.Error("something went wrong"))
        }
    }
}