package com.example.laundrymanager.ViewModels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.laundrymanager.Repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(private val repository: DataStoreRepository): ViewModel() {

    val currentUserToken = repository.getCurrentUserToken.asLiveData()

    fun setUserToken(token: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.setNewUserToken(token)
    }
}