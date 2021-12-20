package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_profile
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.databinding.FragmentMainScreenBinding
import com.tuwaiq.fitnessapp.databinding.FragmentUserProfileBinding


class MainScreen : Fragment() {
    lateinit var binding: FragmentMainScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MainScreen()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val profileVM = ViewModelProvider(this)[VM_profile::class.java]
        val workoutVM = ViewModelProvider(this)[VM_workout::class.java]

        binding.lifecycleOwner = this
        profileVM.getUser_()

        profileVM.user.observe(viewLifecycleOwner, {
            binding.userInfoMain = it.first()

        })
        workoutVM.getWorkout(null)
        binding.RVWorkoutTitle.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        workoutVM.plan.observe(viewLifecycleOwner,{
            binding.RVWorkoutTitle.adapter=workout_main_adapter(it)
        })

        binding.tvCore.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToExercisesList("Core")
            findNavController().navigate(action)
        }
        binding.tvLowerbody.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToExercisesList("Lower Body")
            findNavController().navigate(action)
        }
        binding.tvUpperbody.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToExercisesList("Upper Body")
            findNavController().navigate(action)
        }
        binding.tvWarmUp.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToExercisesList("warm up")
            findNavController().navigate(action)
        }
        binding.mainContainerUserInfo.setOnClickListener{
            val action = MainScreenDirections.actionMainScreenToUserProfile()
            findNavController().navigate(action)
        }

        binding.btnGoToAddPlan.setOnClickListener{
            val action = MainScreenDirections.actionMainScreenToCreateWorkoutPlan()
            findNavController().navigate(action)
        }
    }
}