package com.tuwaiq.fitnessapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwaiq.fitnessapp.Api.FitnessRepo
import com.tuwaiq.fitnessapp.data.Exercise
import kotlinx.coroutines.launch

class VM_exercisesList:ViewModel() {
    var exercises=MutableLiveData<List<Exercise>>()
    val repo =FitnessRepo()
    fun getExercises(category:String?){
        viewModelScope.launch {
            exercises.postValue(repo.getExercises(category))
        }
    }

}
