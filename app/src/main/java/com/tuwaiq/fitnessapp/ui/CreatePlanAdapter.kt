package com.tuwaiq.fitnessapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.databinding.CreatePlanItemBinding
import com.tuwaiq.fitnessapp.ItemSelected


class CreatePlanAdapter(  val exercises: List<Exercise>) : RecyclerView.Adapter<CreatePlan_holder>() {
    val plan: MutableList<String> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatePlan_holder {
        val bind: CreatePlanItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.create_plan_item, parent, false
        )
        return CreatePlan_holder(bind)
    }

    override fun onBindViewHolder(holder: CreatePlan_holder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
        holder.itemView.setOnClickListener{
            if (exercise.isSelected) {
                holder.card.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.white))
                exercise.isSelected=false
            }else{
                holder.card.setCardBackgroundColor(holder.itemView.resources.getColor(R.color.gray))
                exercise.isSelected=true
                plan.add(exercise.exercise_id)
            }
        }
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

}


class CreatePlan_holder(val binding: CreatePlanItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val card: CardView = itemView.findViewById(R.id.createItem_cardView)
    //val plan: MutableList<String> = mutableListOf()
    val itemSelected: ItemSelected? = null

    fun bind(exercise: Exercise) {
        binding.createWorkout = exercise
        if (exercise.isSelected) {
            binding.createItemCardView.setCardBackgroundColor(itemView.resources.getColor(R.color.gray))
        }
    }

}