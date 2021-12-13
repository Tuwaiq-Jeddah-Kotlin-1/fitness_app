package com.tuwaiq.fitnessapp.Api

import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FitnessRepo {
        private val tag ="FlickerRepo"
        private val api= FitnessBuilder.fitnessApi
        suspend fun getExercises(): Exercise = withContext(Dispatchers.IO){
            api.getExercises()}

        suspend fun getUser(searchKeyword:String): User = withContext(Dispatchers.IO){
            api.getUser(searchKeyword)
        }
}