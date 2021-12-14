package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tuwaiq.fitnessapp.VM_profile
import com.tuwaiq.fitnessapp.databinding.FragmentUserProfileBinding


class UserProfile : Fragment() {
    private lateinit var binding:FragmentUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    companion object {
        fun newInstance() = UserProfile()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentUserProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[VM_profile::class.java]
        binding.lifecycleOwner = this
        viewModel.getUser_()
        viewModel.user.observe(viewLifecycleOwner,{
            binding.vmProfile = it.first()

        })

        Log.e("user profile fragment ",viewModel.user.toString())

    }

}