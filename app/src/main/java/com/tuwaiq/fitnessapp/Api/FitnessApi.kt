package com.tuwaiq.fitnessapp.Api

import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.Workout
import retrofit2.Response
import retrofit2.http.*

interface FitnessApi {

    @GET("users")
    suspend fun getUser(@Query("email") email:String ):List<User>

    @GET("exercise")
    suspend fun getExercises(@Query("category") category:String? ):List<Exercise>

    @GET("exercise")
    suspend fun getExercisesById(@Query("exercise_id") id:String? ):List<Exercise>

    @GET("workout_Plans")
    suspend fun getPlan(@Query("id") id:String? ):List<Workout>

    @POST("users")
    suspend fun addUser(@Body user: User): User
    @POST("workout_Plans")
    suspend fun addPlan(@Body plan: Workout)

}
