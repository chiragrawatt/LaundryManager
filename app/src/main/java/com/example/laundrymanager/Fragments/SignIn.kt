package com.example.laundrymanager.Fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.laundrymanager.Models.UserRequest
import com.example.laundrymanager.R
import com.example.laundrymanager.Utils.NetworkResult
import com.example.laundrymanager.Utils.TokenManager
import com.example.laundrymanager.ViewModels.AuthenticationViewModel
import com.example.laundrymanager.ViewModels.SessionViewModel
import com.example.laundrymanager.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignIn : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val authenticationViewModel : AuthenticationViewModel by viewModels()
    //private val sessionViewModel: SessionViewModel by activityViewModels()
    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentSignInBinding.inflate(layoutInflater)

        binding.btnSignIn.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val validationResult = validateCredentials(email, password)
            if(validationResult.first) {
                authenticationViewModel.signIn(UserRequest(email, password))
            } else {
                showSnackBar(validationResult.second)
            }
        }

        bindObserver()

        return binding.root
    }

    private fun validateCredentials(email: String, password: String) : Pair<Boolean, String> {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            return Pair(false, "Please provide the credentials!")
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return Pair(false, "Please provide a valid email address!")
        } else if(password.length<=7) {
            return Pair(false, "Password length should be greater than ")
        }
        return Pair(true, "");
    }

    private fun bindObserver() {
        authenticationViewModel.userResponse.observe(viewLifecycleOwner, Observer {
            try {
                when(it) {
                    is NetworkResult.Success -> {
                        tokenManager.saveToken(it.data!!.token)
                        Log.d("testingSignIn", "New user token: ${it.data.token}")
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