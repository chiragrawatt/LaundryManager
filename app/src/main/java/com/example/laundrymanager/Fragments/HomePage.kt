package com.example.laundrymanager.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.laundrymanager.R
import com.example.laundrymanager.databinding.FragmentHomePageBinding
import com.example.laundrymanager.databinding.FragmentSignInBinding

class HomePage : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater)

        return binding.root
    }
}