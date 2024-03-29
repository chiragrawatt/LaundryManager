package com.example.laundrymanager.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.laundrymanager.R
import com.example.laundrymanager.Repository.DataStoreRepository
import com.example.laundrymanager.Utils.TokenManager
import com.example.laundrymanager.Utils.UserTypeManager
import com.example.laundrymanager.Utils.Utilities
import com.example.laundrymanager.ViewModels.SessionViewModel
import com.example.laundrymanager.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomePage : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    @Inject
    lateinit var tokenManager: TokenManager
    @Inject
    lateinit var userTypeManager: UserTypeManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomePageBinding.inflate(layoutInflater)

        if(tokenManager.getToken()!=null) {
            Log.d("testingHomePage", "Token: ${tokenManager.getToken()}")
        } else {
            Log.d("testing","Empty")
        }

        binding.btnPlaceOrder.setOnClickListener {
            val userType = userTypeManager.getUserType()
            if(userType==null) {
                findNavController().navigate(R.id.action_homePage_to_welcomePage)
            } else {
                if(!userType.toBoolean()) {
                    findNavController().navigate(R.id.action_homePage_to_QRCodeGenerator)
                } else {

                }
            }
        }

        binding.btnViewOrders.setOnClickListener {
            findNavController().navigate(R.id.action_homePage_to_orders)
        }

        binding.btnLogOut.setOnClickListener {
            tokenManager.removeToken()
            userTypeManager.removeUserType()
            findNavController().navigate(R.id.action_homePage_to_welcomePage)
        }

        return binding.root
    }
}