package com.tuwaiq.fitnessapp.Api

import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FitnessRepo {
    private val tag = "FlickerRepo"
    private val api = FitnessBuilder.fitnessApi
    val fireRepo= Firebase_repo()
     val firebaseUserEmail:String=fireRepo.firebaseUserEmail

    suspend fun getExercises(): Exercise = withContext(Dispatchers.IO) {
        api.getExercises()
    }

    suspend fun getUser(): List<User> = withContext(Dispatchers.IO) {
        api.getUser(firebaseUserEmail)

    }
    suspend fun getAllUsers(): users = withContext(Dispatchers.IO) {
        api.getAllUsers()
    }

    suspend fun addUser(user: User) {
        withContext(Dispatchers.IO) { api.addUser(user) }
    }
}