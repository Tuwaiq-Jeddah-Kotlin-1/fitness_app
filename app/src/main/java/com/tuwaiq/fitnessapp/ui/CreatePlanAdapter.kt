package com.tuwaiq.fitnessapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.databinding.CreatePlanItemBinding
import com.tuwaiq.fitnessapp.ItemSelected
import java.util.*
import kotlin.collections.ArrayList


class CreatePlanAdapter(  val exercises: MutableList<Exercise>) : RecyclerView.Adapter<CreatePlan_holder>(),Filterable {
    val plan: MutableList<String> = mutableListOf()
    var filterList:MutableList<Exercise> = mutableListOf()
    init {
        filterList = exercises
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatePlan_holder {
        val bind: CreatePlanItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.create_plan_item, parent, false
        )
        return CreatePlan_holder(bind)
    }

    override fun onBindViewHolder(holder: CreatePlan_holder, position: Int) {
        val exercise = filterList[position]
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
        return filterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = exercises
                } else {
                    val resultList = mutableListOf<Exercise>()
                    for (row in exercises) {
                        if (row.exercise_name.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<Exercise>
                notifyDataSetChanged()
            }

        }
    }

}


class CreatePlan_holder(val binding: CreatePlanItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val card: CardView = itemView.findViewById(R.id.createItem_cardView)
    //val plan: MutableList<String> = mutableListOf()
    val itemSelected: ItemSelected? = null

    fun bind(exercise: Exercise) {
        binding.createWorkout = exercise

    }

}