package com.tuwaiq.fitnessapp.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_exercisesList
import com.tuwaiq.fitnessapp.VM_profile
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.Workout
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
        val exerciseVM = ViewModelProvider(this)[VM_exercisesList::class.java]

        binding.lifecycleOwner = this

        // to add user info in the sharedPreferences
        val UserPrefrence = requireContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val gson = Gson()
        var jsonGET = UserPrefrence.getString("userObj", "")
        var userObjGet = gson.fromJson(jsonGET, User::class.java)
        // check if the user already there if not add it
        if (userObjGet == null) {
            profileVM.getUser_()
            profileVM.user.observe(viewLifecycleOwner, {
                // binding.userInfoMain = it.first()
                val gson = Gson()
                val json = gson.toJson(it.first())
                UserPrefrence
                    .edit()
                    .putString("userObj", json)
                    .apply()
            })
            jsonGET = UserPrefrence.getString("userObj", "")
            userObjGet = gson.fromJson(jsonGET, User::class.java)
        }

        binding.userInfoMain = userObjGet


        // initialize the workout title adapter
        var mainScreenAdaper: workout_main_adapter
        workoutVM.getWorkout(userObjGet.id)
        binding.RVWorkoutTitle.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //fill up the adapter with workouts list + implements swipe to delete functionality
        workoutVM.plan.observe(viewLifecycleOwner, {
            val listOfAllExercises=mutableListOf<List<Exercise>>()
            for(i in it){
                workoutVM.getExercises(i)
                workoutVM.exercises.observe(viewLifecycleOwner,{list->
                    listOfAllExercises.add(list)
                })
            }

            mainScreenAdaper = workout_main_adapter(it, workoutVM, requireContext(),listOfAllExercises)
            val swipToDelete = ItemTouchHelper(SwipeToDeleteCallback(mainScreenAdaper))
            swipToDelete.attachToRecyclerView(binding.RVWorkoutTitle)
            binding.RVWorkoutTitle.adapter = mainScreenAdaper

        })


        // 4 buttons leads to screen show the exercise list by the category
        binding.tvCore.setOnClickListener {
            exerciseVM.getExercises("Core")
            exerciseVM.exercises.observe(viewLifecycleOwner, {
                val action = MainScreenDirections.actionMainScreenToExercisesList(it.toTypedArray())
                findNavController().navigate(action)
            })

        }
        binding.tvLowerbody.setOnClickListener {
            exerciseVM.getExercises("Lower Body")
            exerciseVM.exercises.observe(viewLifecycleOwner, {
                val action = MainScreenDirections.actionMainScreenToExercisesList(it.toTypedArray())
                findNavController().navigate(action)
            })
        }
        binding.tvUpperbody.setOnClickListener {
            exerciseVM.getExercises("Upper Body")
            exerciseVM.exercises.observe(viewLifecycleOwner, {
                val action = MainScreenDirections.actionMainScreenToExercisesList(it.toTypedArray())
                findNavController().navigate(action)
            })
        }
        binding.tvWarmUp.setOnClickListener {
            exerciseVM.getExercises("warm up")
            exerciseVM.exercises.observe(viewLifecycleOwner, {
                val action = MainScreenDirections.actionMainScreenToExercisesList(it.toTypedArray())
                findNavController().navigate(action)
            })
        }

        // click on the user information up the screen to go to the user profile
        binding.mainContainerUserInfo.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToUserProfile()
            findNavController().navigate(action)
        }

        // add a workout plan
        binding.btnGoToAddPlan.setOnClickListener {
            val action = MainScreenDirections.actionMainScreenToCreateWorkoutPlan()
            findNavController().navigate(action)
        }
    }
}