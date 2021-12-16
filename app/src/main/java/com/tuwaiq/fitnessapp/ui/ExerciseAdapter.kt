package com.tuwaiq.fitnessapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_exercisesList
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.databinding.ExerciseItemBinding

class ExerciseAdapter(val exercises: List<Exercise>) : RecyclerView.Adapter<CustomHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val bind: ExerciseItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.exercise_item, parent, false)
        return CustomHolder(bind)
    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)    
    }

    override fun getItemCount(): Int {
       return exercises.size
    }

}

class CustomHolder (val binding :ExerciseItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(exercise: Exercise) {
        binding.vmExerciseItem =exercise
        binding.itemImage.load(exercise.image_url)
       // binding.itemTitle.text=exercise.exercise_name
    }


}
