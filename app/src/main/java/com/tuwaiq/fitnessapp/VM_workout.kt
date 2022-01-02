package com.tuwaiq.fitnessapp

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.fitnessapp.Api.FitnessRepo
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.Workout
import kotlinx.coroutines.launch
import retrofit2.await

class VM_workout : ViewModel() {
    var plan = MutableLiveData<MutableList<Workout>>()
    val exercises = MutableLiveData<MutableList<Exercise>>()

    val repo = FitnessRepo()
    fun getWorkout(id: String?) {
        val workoutmain = ArrayList<Workout>()
        viewModelScope.launch {
            val res1 = repo.getPlan("0")
            val res2 = repo.getPlan(id)
            if (res1.isSuccessful && res2.isSuccessful) {
                workoutmain.addAll(res1.body()!!)
                workoutmain.addAll(res2.body()!!)
            }
            plan.postValue(workoutmain)
        }

    }

    fun getExercises(plan: Workout){
        val ex_list = plan.exercise_id
       val exercises_list = mutableListOf<Exercise>()
        viewModelScope.launch {
            for (i in ex_list) {
                val res = repo.getExercisesById(i)
                if (res.isSuccessful) {
                    exercises_list.add(res.body()!!.first())
                }
                //  Log.e("test----------- $i", repo.getExercisesById(i).first().toString())
            }
           exercises.postValue(exercises_list)
        }
    }

    fun addWorkout(plan: Workout) {
        viewModelScope.launch {
            repo.addPlan(plan)
        }
    }

    fun deletePlan(workout: Workout): Boolean {

        var returnValue: Boolean = false
        viewModelScope.launch {
            if (workout.user_id == "0") {
                returnValue = false
            } else {
                Log.e("DELETE----", workout.id)
                val res = repo.deletePlan(workout)
                returnValue = true
                plan.value?.remove(workout)
                plan.value = plan.value
            }
        }
        return returnValue
    }


}