package com.example.laundrymanager.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laundrymanager.Repository.APIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class APIViewModel(private val repository: APIRepository) : ViewModel() {
    init {
        GlobalScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.testURL()
            }
        }
    }

     val msg : LiveData<String>
     get() = repository.msg
}