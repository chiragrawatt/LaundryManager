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
import com.example.laundrymanager.Models.UserRequest
import com.example.laundrymanager.R
import com.example.laundrymanager.Utils.NetworkResult
import com.example.laundrymanager.ViewModels.APIViewModel
import com.example.laundrymanager.ViewModels.SessionViewModel
import com.example.laundrymanager.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val apiViewModel : APIViewModel by viewModels()
    private val sessionViewModel: SessionViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSignInBinding.inflate(layoutInflater)

        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            apiViewModel.signIn(UserRequest(email, password))
        }

        bindObserver()

        return binding.root
    }

    private fun bindObserver() {
        apiViewModel.userResponse.observe(viewLifecycleOwner, Observer {
            try {
                when(it) {
                    is NetworkResult.Success -> {
                        sessionViewModel.setNewUser(it.data!!.user.userid, it.data.user.type)
                        Log.d("testingSignIn", "New user: ${it.data.user.userid}")
                        findNavController().navigate(R.id.action_signIn_to_homePage)
                    }
                    is NetworkResult.Error -> {
                        Log.d("testingSignIn", "Error msg: ${it.message}")
                        showSnackBar(it.message!!)
                    }
                    is NetworkResult.Loading -> {

                    }
                }
            } catch (ex: Exception) {
                Log.d("testingSignIn", ex.toString())
            }
        })
    }

    private fun showSnackBar(message: String) {
        val snackBar: Snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

}