package com.tuwaiq.fitnessapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.databinding.ExerciseItemBinding
import com.tuwaiq.fitnessapp.databinding.FragmentPlayExerciseItemBinding
import com.tuwaiq.fitnessapp.databinding.WorkoutItemBinding

class Adapter_ViewPager(val playList: List<Exercise>) :
    RecyclerView.Adapter<Adapter_ViewPager.pagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pagerViewHolder {
        val bind: FragmentPlayExerciseItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.fragment_play_exercise_item, parent, false
        )
        return pagerViewHolder(bind)
    }

    override fun onBindViewHolder(holder: pagerViewHolder, position: Int) {
        val exercise = playList[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
return playList.size
    }

    inner class pagerViewHolder(val binding: FragmentPlayExerciseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            binding.playexercise = exercise
            binding.playExerciseImage.load(exercise.image_url)
        }
    }

}