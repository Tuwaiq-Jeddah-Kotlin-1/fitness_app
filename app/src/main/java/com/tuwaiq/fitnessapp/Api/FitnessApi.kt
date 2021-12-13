package com.tuwaiq.fitnessapp.Api

import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.PUT
import retrofit2.http.POST

interface FitnessApi {
    @GET("/users")
    suspend fun getUser(@Query("text") user:String ): User
    @GET("/exercise")
    suspend fun getExercises(): Exercise

}
