package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_exercisesList
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.databinding.FragmentExercisesListBinding
import com.tuwaiq.fitnessapp.databinding.FragmentWorkoutExercisesListBinding


class workout_exercises_list : Fragment() {
    lateinit var binding: FragmentWorkoutExercisesListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWorkoutExercisesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        fun newInstance() = workout_exercises_list()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[VM_workout::class.java]
        binding.lifecycleOwner = this
        binding.planExerciseRv.layoutManager = LinearLayoutManager(requireContext())
        val args: workout_exercises_listArgs by navArgs()
        val workout = args.workoutList
        viewModel.getExercises(workout)
        viewModel.exercises.observe(viewLifecycleOwner, {exerciseList ->
            binding.planExerciseRv.adapter = WorkoutExerciseListAdapter(exerciseList)
            binding.btnPlanStartExercise.setOnClickListener {
                val action =workout_exercises_listDirections.actionWorkoutExercisesListToPlayExercisesList(exerciseList.toTypedArray())
                findNavController().navigate(action)
            }
        })

    }
}