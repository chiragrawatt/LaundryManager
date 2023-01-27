package com.example.laundrymanager.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.laundrymanager.R
import com.example.laundrymanager.Utils.TokenManager
import com.example.laundrymanager.ViewModels.SessionViewModel
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomePageBinding.inflate(layoutInflater)

        if(tokenManager.getToken()!=null) {
            findNavController().navigate(R.id.action_welcomePage_to_homePage)
        } else {
            Log.d("testing","Empty")
        }


        binding.btnSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomePage_to_signIn)
        }

        binding.btnSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomePage_to_signUp)
        }

        return binding.root
    }
}