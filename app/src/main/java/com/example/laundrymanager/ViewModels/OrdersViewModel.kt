package com.example.laundrymanager.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laundrymanager.Models.UserRequest
import com.example.laundrymanager.Models.UserResponse
import com.example.laundrymanager.Repository.APIRepository
import com.example.laundrymanager.Utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrdersViewModel(private val repository: APIRepository) : ViewModel() {

}