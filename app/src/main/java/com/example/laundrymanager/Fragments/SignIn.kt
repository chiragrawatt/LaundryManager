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
            apiViewModel.signIn(email, password)
            findNavController().navigate(R.id.action_signIn_to_homePage)
        }

        apiViewModel.userResponse.observe(viewLifecycleOwner, Observer {
            if(it.response=="success") {
                sessionViewModel.setNewUser(it.user.userid, it.user.type)
                sessionViewModel.currentUser.observe(viewLifecycleOwner, Observer {
                    Log.d("testing", "New user: $it")
//                    if(it!="none") {
//                        findNavController().navigate(R.id.action_signIn_to_homePage)
//                    }
                })
            } else {
                showSnackBar(it.response)
            }
        })

        return binding.root
    }

    fun showSnackBar(message: String) {
        val snackbar: Snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackbar.show()
    }

}