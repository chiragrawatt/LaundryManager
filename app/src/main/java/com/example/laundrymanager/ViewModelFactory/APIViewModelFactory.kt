package com.example.laundrymanager.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.laundrymanager.Repository.APIRepository
import com.example.laundrymanager.ViewModels.APIViewModel

class APIViewModelFactory(private val repository: APIRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return APIViewModel(repository) as T
    }

}