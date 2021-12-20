package com.tuwaiq.fitnessapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.tuwaiq.fitnessapp.databinding.FragmentPlayExerciseBinding


class PlayExercise : Fragment() {
    lateinit var binding: FragmentPlayExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() = PlayExercise()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: PlayExerciseArgs by navArgs()
        val exerciseList = args.exercise
        binding.lifecycleOwner = this
        var position: Int = 0
        binding.playexercise = exerciseList[position]
        binding.playExerciseImage.load(exerciseList[position].image_url)
        binding.nextExercise.setOnClickListener {
            if(position>=exerciseList.size-1){
                position=0
            }else{
                position++
            }
            binding.playexercise = exerciseList[position]
            binding.playExerciseImage.load(exerciseList[position].image_url)

        }
        binding.lastExercise.setOnClickListener {
            if(position!=0){
                position--
                binding.playexercise = exerciseList[position]
                binding.playExerciseImage.load(exerciseList[position].image_url)

            }
        }
    }
}