package com.example.laundrymanager.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.laundrymanager.R
import com.example.laundrymanager.ViewModels.SessionViewModel
import com.example.laundrymanager.databinding.FragmentHomePageBinding

class HomePage : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val sessionViewModel: SessionViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater)

        sessionViewModel.currentUserToken.observe(viewLifecycleOwner, Observer {
            Log.d("testingHomePage", "Token: $it")
            if(it=="none") {
                findNavController().navigate(R.id.action_homePage_to_welcomePage)
            }
        })

        binding.btnPlaceOrder.setOnClickListener {

        }

        binding.btnViewOrders.setOnClickListener {
            findNavController().navigate(R.id.action_homePage_to_orders)
        }

        binding.btnLogOut.setOnClickListener {
            sessionViewModel.setUserToken("none")
            findNavController().navigate(R.id.action_homePage_to_welcomePage)
        }

        return binding.root
    }
}