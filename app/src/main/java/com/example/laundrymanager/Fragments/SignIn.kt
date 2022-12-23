package com.example.laundrymanager.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.laundrymanager.R
import com.example.laundrymanager.Repository.APIRepository
import com.example.laundrymanager.Services.LaundryService
import com.example.laundrymanager.Services.ServiceBuilder
import com.example.laundrymanager.ViewModelFactory.APIViewModelFactory
import com.example.laundrymanager.ViewModels.APIViewModel
import com.example.laundrymanager.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class SignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var apiViewModel : APIViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSignInBinding.inflate(layoutInflater)

        val apiService : LaundryService = ServiceBuilder.getInstance().create(LaundryService::class.java)
        val repository = APIRepository(apiService)

        apiViewModel = ViewModelProvider(this, APIViewModelFactory(repository)).get(APIViewModel::class.java)

        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            lifecycleScope.launch(Dispatchers.IO) {
                val userResponse = apiViewModel.signIn(email, password)

                if(userResponse!=null) {
                    showSnackBar(userResponse.response)
                } else {
                    showSnackBar("found null")
                }
            }
        }

        return binding.root
    }

    fun showSnackBar(message: String) {
        val snackbar: Snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

}