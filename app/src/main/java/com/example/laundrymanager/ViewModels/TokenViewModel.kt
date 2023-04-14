package com.example.laundrymanager.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laundrymanager.Models.MessageResponse
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Repository.TokenRepository
import com.example.laundrymanager.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(private val repository: TokenRepository) : ViewModel() {
    val tokenResponse: LiveData<NetworkResult<MessageResponse>>
    get() = repository.tokenResponse

    fun checkToken() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkToken()
        }
    }
}