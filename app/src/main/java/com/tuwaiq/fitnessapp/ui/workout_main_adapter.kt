package com.tuwaiq.fitnessapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.Workout
import com.tuwaiq.fitnessapp.databinding.ExerciseItemBinding
import com.tuwaiq.fitnessapp.databinding.WorkoutItemBinding

class workout_main_adapter (val workout: List<Workout>) : RecyclerView.Adapter<Workout_Title_Holder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Workout_Title_Holder {
        val bind: WorkoutItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.workout_item, parent, false)
        return Workout_Title_Holder(bind)
    }

    override fun onBindViewHolder(holder: Workout_Title_Holder, position: Int) {
        val workout = workout[position]
        holder.bind(workout)
        holder.itemView.setOnClickListener{
       val action=MainScreenDirections.actionMainScreenToWorkoutExercisesList(workout)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return workout.size
    }

}

class Workout_Title_Holder (val binding : WorkoutItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(workout: Workout) {
        binding.workoutItem=workout
        // binding.itemTitle.text=exercise.exercise_name
    }


}