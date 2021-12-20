package com.tuwaiq.fitnessapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.databinding.CreatePlanItemBinding
import com.tuwaiq.fitnessapp.databinding.ExerciseItemBinding
import android.os.Bundle




class CreatePlanAdapter(val exercises: List<Exercise>) : RecyclerView.Adapter<CreatePlan_holder>() {
     var checked_exercises=mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatePlan_holder {
        val bind: CreatePlanItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.create_plan_item, parent, false
        )
        return CreatePlan_holder(bind)
    }

    override fun onBindViewHolder(holder: CreatePlan_holder, position: Int) {
        checked_exercises= mutableListOf()
        val exercise = exercises[position]
        holder.bind(exercise)

        if(holder.checkBox.isChecked){
            checked_exercises.add(exercise.exercise_id)

        }
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}

class CreatePlan_holder(val binding: CreatePlanItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val checkBox: CheckBox = itemView.findViewById(R.id.checkBox_exercise)
    val plan: MutableList<String> = mutableListOf()
    fun bind(exercise: Exercise) {
        binding.createWorkout = exercise

    }


}
