package com.tuwaiq.fitnessapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.Auth.Response
import com.tuwaiq.fitnessapp.VM_Auth
import com.tuwaiq.fitnessapp.VM_profile
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.databinding.FragmentLoginBinding


class Login : Fragment(), Response {
    private lateinit var binding: FragmentLoginBinding
    lateinit var UserPrefrence: SharedPreferences
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() = Login()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  val repo = Firebase_repo()
        val currentuser = auth.currentUser
        val viewModel = ViewModelProvider(this)[VM_Auth::class.java]
        val profileVM = ViewModelProvider(this)[VM_profile::class.java]
        binding.lifecycleOwner = this

        UserPrefrence = requireContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        binding.vmSignIn = viewModel



        // save the login state
        if (auth.currentUser != null) {
            val action = LoginDirections.actionLoginToMainScreen()
            findNavController().navigate(action)
        }

        //login and transfer to the main screen
        binding.button.setOnClickListener {
            viewModel.logIn(findNavController())

        }

        //transfer to the signUp screen
        binding.btnSignupLogin.setOnClickListener {
            val action = LoginDirections.actionLoginToSignUp()
            findNavController().navigate(action)
        }

    }

    override fun success(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun fail(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }


}