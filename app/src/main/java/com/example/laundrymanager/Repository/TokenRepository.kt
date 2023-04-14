package com.example.laundrymanager.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laundrymanager.Models.MessageResponse
import com.example.laundrymanager.Services.TokenAPI
import com.example.laundrymanager.Utils.NetworkResult
import org.json.JSONObject

class TokenRepository constructor(private val tokenAPI: TokenAPI) {
    private val _tokenResponse = MutableLiveData<NetworkResult<MessageResponse>>()

    val tokenResponse: LiveData<NetworkResult<MessageResponse>>
    get() = _tokenResponse

    suspend fun checkToken() {
        try {
            val response = tokenAPI.checkToken()
            _tokenResponse.postValue(NetworkResult.Loading())
            if(response.isSuccessful) {
                _tokenResponse.postValue(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _tokenResponse.postValue(NetworkResult.Error(errorObj.getString("message")))
            } else {
                _tokenResponse.postValue(NetworkResult.Error("something went wrong"))
            }
        }catch(ex: Exception) {
            Log.d("testingTokenRep", ex.toString())
        }
    }
}