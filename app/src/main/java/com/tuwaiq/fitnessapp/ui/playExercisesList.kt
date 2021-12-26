package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.databinding.FragmentPlayExercisesListBinding
import com.tuwaiq.fitnessapp.databinding.FragmentWorkoutExercisesListBinding

class playExercisesList : Fragment() {
    lateinit var binding: FragmentPlayExercisesListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayExercisesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = playExercisesList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        val args: playExercisesListArgs by navArgs()
        val listToPlay = args.listToPlay
       binding.playViewPager.adapter=Adapter_ViewPager(listToPlay.toList())
        binding.playViewPager.orientation=ViewPager2.ORIENTATION_HORIZONTAL
        binding.playIndicator.setViewPager(binding.playViewPager)

    }
}