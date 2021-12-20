package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_exercisesList
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.data.Exercise
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
        return binding.root    }

    companion object {
        fun newInstance() = create_workoutPlan()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lifecycleOwner = this
        val viewModel = ViewModelProvider(this)[VM_exercisesList::class.java]
        val planViewModel = ViewModelProvider(this)[VM_workout::class.java]

        binding.RVCreatePlan.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getExercises(null)
        var exList=listOf<Exercise>()
        viewModel.exercises.observe(viewLifecycleOwner,{
            exList=it
            binding.RVCreatePlan.adapter=CreatePlanAdapter(it)

        })

        binding.addplan.setOnClickListener {
            Log.e("test adding plan","list $exList")
            val list=CreatePlanAdapter(exList).checked_exercises
                Log.e("test adding plan","observe $list")
                planViewModel.addWorkout(Workout("1","title",list.toTypedArray()))

        }
    }
}