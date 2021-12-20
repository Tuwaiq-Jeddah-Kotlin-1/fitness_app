package com.tuwaiq.fitnessapp.Api

import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FitnessRepo {
    private val tag = "FlickerRepo"
    private val api = FitnessBuilder.fitnessApi
    val fireRepo= Firebase_repo()
     val firebaseUserEmail:String=fireRepo.firebaseUserEmail

    suspend fun getExercises(category:String?): List<Exercise> = withContext(Dispatchers.IO) {
        api.getExercises(category)
    }
    suspend fun getExercisesById(id:String?): List<Exercise> = withContext(Dispatchers.IO) {
        api.getExercisesById(id)
    }
    suspend fun getUser(): List<User> = withContext(Dispatchers.IO) {
        api.getUser(firebaseUserEmail)

    }
    suspend fun getPlan(id:String?): List<Workout> = withContext(Dispatchers.IO) {
        api.getPlan(id)
    }
    suspend fun addUser(user: User) {
        withContext(Dispatchers.IO) { api.addUser(user) }
    }
    suspend fun addPlan(plan: Workout) {
        withContext(Dispatchers.IO) { api.addPlan(plan) }
    }
}