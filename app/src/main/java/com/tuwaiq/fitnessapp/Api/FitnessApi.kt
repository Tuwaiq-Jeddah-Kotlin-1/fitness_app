package com.tuwaiq.fitnessapp.Api

import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.users
import retrofit2.Response
import retrofit2.http.*

interface FitnessApi {

    @GET("users")
    suspend fun getUser(@Query("email") email:String ):List<User>

    @GET("exercise")
    suspend fun getExercises(@Query("category") category:String ):List<Exercise>

    @GET("users")
    suspend fun getAllUsers(): List<users>

/*    @GET("exercise")
    suspend fun getExercises(): List<Exercise>*/
    @POST("users")
    suspend fun addUser(@Body user: User): User


}
