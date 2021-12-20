package com.tuwaiq.fitnessapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.Workout
import com.tuwaiq.fitnessapp.databinding.ExerciseItemBinding
import com.tuwaiq.fitnessapp.databinding.WorkoutItemBinding

class WorkoutExerciseListAdapter (val exercises: List<Exercise>) : RecyclerView.Adapter<Workout_list_Holder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Workout_list_Holder {
        val bind: ExerciseItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.exercise_item, parent, false)
        return Workout_list_Holder(bind)    }

    override fun onBindViewHolder(holder: Workout_list_Holder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

}

class Workout_list_Holder (val binding : ExerciseItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(exercise: Exercise) {
        binding.vmExerciseItem =exercise
        binding.itemImage.load(exercise.image_url)
    }

}