package com.example.laundrymanager.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.laundrymanager.Services.LaundryService
import retrofit2.Response

class APIRepository(private val laundryService: LaundryService) {

    private val _msg = MutableLiveData<String>("ello its me gmd")

    val msg: LiveData<String>
    get() = _msg

    suspend fun testURL(page: Int) {
        val result: Response<String>
        try {
            result = laundryService.demoQuotes(page)
            if(result.body() != null) {
                _msg.postValue(result.body())
            }
        }catch(ex: Exception) {
            _msg.postValue("nil")
            Log.d("testing", ex.toString())
        }
    }
}