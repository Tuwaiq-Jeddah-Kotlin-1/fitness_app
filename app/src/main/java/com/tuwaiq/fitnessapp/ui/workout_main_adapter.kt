package com.tuwaiq.fitnessapp.ui

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.gms.common.api.internal.LifecycleFragment
import com.tuwaiq.fitnessapp.VM_workout
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.Workout
import com.tuwaiq.fitnessapp.databinding.ExerciseItemBinding
import com.tuwaiq.fitnessapp.databinding.WorkoutItemBinding

import com.tuwaiq.fitnessapp.MainActivity

import androidx.core.content.ContextCompat
import com.tuwaiq.fitnessapp.R
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Toast


class workout_main_adapter (val workout: ArrayList<Workout>,val viewModel:VM_workout
,val context: Context,val listOfList:List<List<Exercise>>) : RecyclerView.Adapter<Workout_Title_Holder>()  {
private lateinit var mListener:OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position :Int)
    }

    fun setOnItemClickListener(listener:OnItemClickListener){
        mListener=listener
    }
    fun deleteItem(position:Int){
        val check=viewModel.deletePlan(workout.get(position))
        if(check){
           Toast.makeText(context,"item deleted",Toast.LENGTH_LONG).show()
        }else{
          //  Log.e("check","id of the workout ${workout.get(position)}")
            Toast.makeText(context,"Sorry, you can't delete fixed plans",Toast.LENGTH_LONG).show()

        }
    }
    fun addItem(position:Int,plan:Workout){
        workout.add(position,plan)
        notifyDataSetChanged()
    }
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
       val action=MainScreenDirections.actionMainScreenToExercisesList(listOfList[position].toTypedArray())
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


class SwipeToDeleteCallback(val adapter: workout_main_adapter):  ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.UP){
     override fun onMove(
         recyclerView: RecyclerView,
         viewHolder: RecyclerView.ViewHolder,
         target: RecyclerView.ViewHolder
     ): Boolean {
return false     }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
       adapter.deleteItem(viewHolder.adapterPosition)
    }


}
