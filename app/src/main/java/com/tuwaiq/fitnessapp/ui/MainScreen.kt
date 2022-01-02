package com.tuwaiq.fitnessapp.ui

import android.annotation.SuppressLint
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
import com.tuwaiq.fitnessapp.*
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

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        val mainVM=ViewModelProvider(this)[VMmain::class.java]
        val workoutVM=ViewModelProvider(this)[VM_workout::class.java]


        // to add user info in the sharedPreferences
        val UserPrefrence = requireContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        // check if the user already there if not add it
        mainVM.getUser()
        mainVM.user.observe(viewLifecycleOwner,{
            val gson = Gson()
            val json = gson.toJson(it)
            UserPrefrence
                .edit()
                .putString("userObj", json)
                .apply()
            mainVM.getWorkout(it.id)

        })
        val jsonGET = UserPrefrence.getString("userObj", "")
        val userObjGet = Gson().fromJson(jsonGET, User::class.java)
        Log.e("gender",userObjGet.gender)

        if (userObjGet.gender == "Female") {

            binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.woman))
        } else {
            binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.ic_man_svgrepo_com))

        }

        binding.userInfoMain = userObjGet


        // initialize the workout title adapter
        var mainScreenAdaper: workout_main_adapter

        binding.RVWorkoutTitle.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //fill up the adapter with workouts list + implements swipe to delete functionality
        mainVM.plan?.observe(viewLifecycleOwner, {
            mainScreenAdaper = workout_main_adapter(it!!, workoutVM, requireContext())
            val swipToDelete = ItemTouchHelper(SwipeToDeleteCallback(mainScreenAdaper))
            swipToDelete.attachToRecyclerView(binding.RVWorkoutTitle)
            binding.RVWorkoutTitle.adapter = mainScreenAdaper

        })

        // 4 buttons leads to screen show the exercise list by the category
        binding.tvCore.setOnClickListener {
            mainVM.getExercises("Core")
            mainVM.exercises.observe(viewLifecycleOwner, {
                val action = MainScreenDirections.actionMainScreenToExercisesList(it.toTypedArray())
                findNavController().navigate(action)
            })

        }
        binding.tvLowerbody.setOnClickListener {
            mainVM.getExercises("Lower Body")
            mainVM.exercises.observe(viewLifecycleOwner, {
                val action = MainScreenDirections.actionMainScreenToExercisesList(it.toTypedArray())
                findNavController().navigate(action)
            })
        }
        binding.tvUpperbody.setOnClickListener {
            mainVM.getExercises("Upper Body")
            mainVM.exercises.observe(viewLifecycleOwner, {
                val action = MainScreenDirections.actionMainScreenToExercisesList(it.toTypedArray())
                findNavController().navigate(action)
            })
        }
        binding.tvWarmUp.setOnClickListener {
            mainVM.getExercises("warm up")
            mainVM.exercises.observe(viewLifecycleOwner, {
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