package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_Auth
import com.tuwaiq.fitnessapp.VM_exercisesList
import com.tuwaiq.fitnessapp.databinding.FragmentExercisesListBinding
import com.tuwaiq.fitnessapp.databinding.FragmentLoginBinding


class ExercisesList : Fragment() {
    private lateinit var binding: FragmentExercisesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() = ExercisesList()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentExercisesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[VM_exercisesList::class.java]
        binding.lifecycleOwner = this
        binding.exerciseRv.layoutManager = LinearLayoutManager(requireContext())

        val args:  ExercisesListArgs by navArgs()
        val category = args.category
        viewModel.getExercises(category)
        viewModel.exercises.observe(viewLifecycleOwner,{
            binding.exerciseRv.adapter=ExerciseAdapter(it)
        })
    }

}