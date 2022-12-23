package com.example.laundrymanager.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Repository.APIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class APIViewModel(private val repository: APIRepository) : ViewModel() {

    suspend fun signIn(email: String, password: String) : UserResponse? {
        return repository.signIn(email, password)
    }
}