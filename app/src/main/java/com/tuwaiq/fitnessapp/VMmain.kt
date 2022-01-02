package com.tuwaiq.fitnessapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.fitnessapp.Api.FitnessRepo
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.Workout
import kotlinx.coroutines.launch

class VMmain():ViewModel() {
    var plan = MutableLiveData<MutableList<Workout>>()
    var exercises = MutableLiveData<List<Exercise>>()

    val user=MutableLiveData<User>()
    val repo=FitnessRepo()
    fun getUser(){
        viewModelScope.launch {
           user.postValue(repo.getUser().first())
        }
    }

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
    fun getExercises(category: String?) {
        viewModelScope.launch {
            val res = repo.getExercises(category)
            if (res.isSuccessful) {
                exercises.postValue(res.body()!!)
            }
        }
    }
}
