package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.databinding.FragmentEndExercisePlayBinding
import com.tuwaiq.fitnessapp.databinding.FragmentExercisesListBinding
import com.tuwaiq.fitnessapp.databinding.FragmentMainScreenBinding


class endExercisePlay : Fragment() {
    lateinit var binding: FragmentEndExercisePlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEndExercisePlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        fun newInstance() = endExercisePlay()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
    }
}