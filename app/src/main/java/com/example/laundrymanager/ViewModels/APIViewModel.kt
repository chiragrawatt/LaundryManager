package com.example.laundrymanager.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laundrymanager.Models.UserRequest
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Repository.APIRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class APIViewModel @Inject constructor(private val repository: APIRepository) : ViewModel() {

    private val _userResponse = repository.userResponse

    val userResponse: LiveData<UserResponse>
    get() = _userResponse

    fun signIn(userRequest: UserRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.signIn(userRequest)
        }
    }
}