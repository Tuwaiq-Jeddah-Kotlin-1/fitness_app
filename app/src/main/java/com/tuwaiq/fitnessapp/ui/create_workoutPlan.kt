package com.tuwaiq.fitnessapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_exercisesList
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.Workout
import com.tuwaiq.fitnessapp.databinding.FragmentCreateWorkoutPlanBinding
import com.tuwaiq.fitnessapp.databinding.FragmentExercisesListBinding


class create_workoutPlan : Fragment() {
lateinit var binding:FragmentCreateWorkoutPlanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateWorkoutPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = create_workoutPlan()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lifecycleOwner = this
        val UserPrefrence = requireContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val getUserInfo= UserPrefrence.getString("userObj","")
        val gson = Gson()
        val json = UserPrefrence.getString("userObj", "")
        val userObj = gson.fromJson(json, User::class.java)
        val viewModel = ViewModelProvider(this)[VM_exercisesList::class.java]
        val planViewModel = ViewModelProvider(this)[VM_workout::class.java]

        binding.RVCreatePlan.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getExercises(null)
        var exList=listOf<Exercise>()
        var adapter:CreatePlanAdapter?=null
       // var selectedItems=mutableListOf<String>()

        viewModel.exercises.observe(viewLifecycleOwner,{
            exList=it
            adapter=CreatePlanAdapter(it.toMutableList())
            binding.RVCreatePlan.adapter=adapter

         //   adapter?.getItemSelected()
        })
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })

        binding.addplan.setOnClickListener {

            Log.e("test adding plan","list $exList")
            Log.e("test adding plan","observe ${adapter?.plan}")
            planViewModel.addWorkout(Workout("",userObj.id,"title",adapter?.plan!!.toTypedArray()))
        }
    }
}