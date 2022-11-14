package com.example.laundrymanager.ViewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.laundrymanager.Repository.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SessionViewModel(application: Application): AndroidViewModel(application) {

    private val repository = DataStoreRepository.getInstance(application)

    val currentUser = repository.getCurrentUser.asLiveData()

    fun setNewUser(userId: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.setNewUser(userId)
    }
}