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

        sessionViewModel.currentUserId.observe(viewLifecycleOwner, Observer {
            Log.d("testingd", "Type: $it")
            if(it!=-1) {
                if(it==0) {
                    binding.btnPerformAction.text = "Place Order"
                } else {
                    binding.btnPerformAction.text = "Take Order"
                }
            } else {
                //findNavController().navigate(R.id.action_homePage_to_welcomePage)
            }
        })

        binding.btnPerformAction.setOnClickListener {

        }

        binding.btnViewOrders.setOnClickListener {
            
        }

        binding.btnLogOut.setOnClickListener {
            sessionViewModel.setNewUser("none", -1)
            findNavController().navigate(R.id.action_homePage_to_welcomePage)
        }

        return binding.root
    }
}