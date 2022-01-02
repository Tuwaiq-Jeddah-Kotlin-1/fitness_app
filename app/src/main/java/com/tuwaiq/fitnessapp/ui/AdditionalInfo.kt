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
import androidx.navigation.fragment.navArgs
import com.tuwaiq.fitnessapp.Auth.Response
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_profile
import com.tuwaiq.fitnessapp.databinding.FragmentAdditionalInfoBinding
import com.tuwaiq.fitnessapp.databinding.FragmentUserProfileBinding


class AdditionalInfo : Fragment(), Response {
    lateinit var binding: FragmentAdditionalInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAdditionalInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        fun newInstance() = AdditionalInfo()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this)[VM_profile::class.java]
        binding.vmAdditional = viewModel
       // viewModel.getUser_()
        val args: AdditionalInfoArgs by navArgs()
        val user = args.user
        binding.btnSkip.setOnClickListener {
            val action = AdditionalInfoDirections.actionAdditionalInfoToLogin()
            findNavController().navigate(action)

        }
        binding.btnAddAditional.setOnClickListener {
            Log.e("clicked", "add button clicked")

            Log.e("USER", user.toString())
          viewModel.getUser()
            viewModel.user.observe(viewLifecycleOwner, {
                Log.e("user observer", it.toString())
                val result = viewModel.AdditionalupdateUser(it.id)
                if (result) {
                    Log.e("if the result succeed", "inside the observer")
                    val action = AdditionalInfoDirections.actionAdditionalInfoToLogin()
                    findNavController().navigate(action)
                } else {
                    Log.e("if the result FALSE", "inside the observer")

                }
           })

            Log.e("outside", "the observer")


        }
    }

    override fun success(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    override fun fail(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}