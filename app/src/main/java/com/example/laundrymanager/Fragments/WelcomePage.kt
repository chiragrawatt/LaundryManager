package com.example.laundrymanager.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.laundrymanager.R
import com.example.laundrymanager.databinding.FragmentWelcomePageBinding

class WelcomePage : Fragment() {

    private lateinit var binding: FragmentWelcomePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomePageBinding.inflate(layoutInflater)

        binding.btnSignIn.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomePage_to_signIn3)
        }

        binding.btnSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_welcomePage_to_signUp)
        }

        return binding.root
    }

}