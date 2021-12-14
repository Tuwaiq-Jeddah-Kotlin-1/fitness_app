package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.fitnessapp.Auth.Response
import com.tuwaiq.fitnessapp.VM_Auth
import com.tuwaiq.fitnessapp.databinding.FragmentLoginBinding


class Login : Fragment(),Response {
private lateinit var binding:FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    companion object {
        fun newInstance()=Login()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val auth = FirebaseAuth.getInstance()
       // val currentuser = auth.currentUser
        val viewModel = ViewModelProvider(this)[VM_Auth::class.java]
        binding.lifecycleOwner = this
        binding.vmSignIn = viewModel

        binding.button.setOnClickListener{
            viewModel.logIn( findNavController())
        }

        binding.btnSignupLogin.setOnClickListener{
            val action = LoginDirections.actionLoginToSignUp()
            findNavController().navigate(action)
        }

    }

    override fun success(msg: String) {
     Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
    }

    override fun fail(msg: String) {
        Toast.makeText(requireContext(),msg,Toast.LENGTH_LONG).show()
    }


}