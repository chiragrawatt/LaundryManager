package com.example.laundrymanager.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.laundrymanager.R
import com.example.laundrymanager.databinding.FragmentSignUpBinding


class SignUp : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSignUpBinding.inflate(layoutInflater)

        return binding.root
    }


}