package com.tuwaiq.fitnessapp.Api

import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.await

class FitnessRepo {
    private val tag = "FlickerRepo"
    private val api = FitnessBuilder.fitnessApi
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    val fireRepo= Firebase_repo()
    //val firebaseUserEmail:String?=fireRepo.firebaseUserEmail

    suspend fun getExercises(category:String?): Response<List<Exercise>> = withContext(Dispatchers.IO) {
        api.getExercises(category)
    }
    suspend fun getExercisesById(id:String?): Response<List<Exercise>> = withContext(Dispatchers.IO) {
        api.getExercisesById(id)
    }
    suspend fun getUser(): List<User> = withContext(Dispatchers.IO) {
        api.getUser(auth.currentUser!!.email!!)

    }

    suspend fun getPlan(id:String?): Response<List<Workout>> = withContext(Dispatchers.IO) {
        api.getPlan(id)
    }
    suspend fun addUser(user: User) {
        withContext(Dispatchers.IO) { api.addUser(user)}
    }
    suspend fun addPlan(plan: Workout) {
        withContext(Dispatchers.IO) { api.addPlan(plan)}
    }

    suspend fun deletePlan(plan: Workout)=
        withContext(Dispatchers.IO) { api.deletePlan(plan.id).await() }

    suspend fun updateUser(id:String,weight:Int,height:Int,bmi:String):Response<User> =
        withContext(Dispatchers.IO) { api.updateUser(id,weight,height,bmi) }

    suspend fun updateUserObj(id:String,name:String,weight:Int,height:Int,bmi:String):Response<User> =
        withContext(Dispatchers.IO) { api.updateUserObject(id,name,weight,height,bmi) }


}