package com.tuwaiq.fitnessapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.fitnessapp.Api.FitnessRepo
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.Workout
import kotlinx.coroutines.launch

class VM_workout : ViewModel() {
    var plan = MutableLiveData<List<Workout>>()
    var exercises = MutableLiveData<List<Exercise>>()

    val repo = FitnessRepo()
    fun getWorkout(id: String?) {
        viewModelScope.launch {
            plan.postValue(repo.getPlan(id))
        }
    }

    fun getExercises(plan: Workout) {
        val ex_list = plan.exercise_id
        val exercises_list = mutableListOf<Exercise>()
        viewModelScope.launch {
            for (i in ex_list) {
                Log.e("test----------- $i", repo.getExercisesById(i).first().toString())
                exercises_list.add(repo.getExercisesById(i).first())
            }
            Log.e("test----------- $exercises_list", "WILL SEE")
            exercises.postValue(exercises_list)
        }
    }
    fun addWorkout(plan: Workout) {
        viewModelScope.launch {
            repo.addPlan(plan)
        }
    }

}