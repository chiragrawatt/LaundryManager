package com.example.laundrymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.laundrymanager.ViewModels.SessionViewModel
import com.example.laundrymanager.databinding.ActivityMainBinding

class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionViewModel: SessionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionViewModel = ViewModelProvider(this).get(SessionViewModel::class.java)

        sessionViewModel.currentUser.observe(this, {user ->
            binding.txtOutput.text = user
        })

        binding.btnAction.setOnClickListener {
            sessionViewModel.setNewUser(binding.edtInput.text.toString())
        }
    }
}