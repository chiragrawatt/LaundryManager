package com.example.laundrymanager.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.laundrymanager.R
import com.example.laundrymanager.Services.OrdersAPI
import com.example.laundrymanager.Utils.NetworkResult
import com.example.laundrymanager.Utils.TokenManager
import com.example.laundrymanager.ViewModels.SessionViewModel
import com.example.laundrymanager.ViewModels.TokenViewModel
import com.example.laundrymanager.databinding.FragmentWelcomePageBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomePage : Fragment() {

    private lateinit var binding: FragmentWelcomePageBinding
    //private val sessionViewModel : SessionViewModel by activityViewModels()
    @Inject
    lateinit var tokenManager: TokenManager

    private val tokenViewModel: TokenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomePageBinding.inflate(layoutInflater)

        bindObservers()

        tokenViewModel.checkToken()

        binding.btnSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomePage_to_signIn)
        }

        binding.btnSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomePage_to_signUp)
        }

        return binding.root
    }

    private fun bindObservers() {
        tokenViewModel.tokenResponse.observe(viewLifecycleOwner, Observer {
            try {
                when(it) {
                    is NetworkResult.Success -> {
                        Log.d("testingWelcomeSuccess", it.data!!.message)
                        findNavController().navigate(R.id.action_welcomePage_to_homePage)
                    }
                    is NetworkResult.Error -> {
                        Log.d("testingWelcomeError", "Error msg: ${it.message}")
                    }
                    is NetworkResult.Loading -> {
                        Log.d("testingWelcomeLoading", "Loading")
                    }
                }
            } catch (ex: Exception) {
                Log.d("testingWelcome", ex.toString())
            }
        })
    }
}