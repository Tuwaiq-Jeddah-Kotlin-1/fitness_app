package com.tuwaiq.fitnessapp.Api

import com.tuwaiq.fitnessapp.data.Exercise
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.data.Workout
import retrofit2.Response
import retrofit2.http.*
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.DELETE
import retrofit2.http.Body

import retrofit2.http.PATCH







interface FitnessApi {

    @GET("users")
    suspend fun getUser(@Query("email") email:String ):List<User>

    @GET("/exercise")
    suspend fun getExercises(@Query("category") category:String? ):Response<List<Exercise>>

    @GET("/exercise")
    suspend fun getExercisesById(@Query("exercise_id") id:String? ):Response<List<Exercise>>

    @GET("/workout_Plans")
    suspend fun getPlan(@Query("user_id") user_id:String? ):Response<List<Workout>>

    @POST("users")
    suspend fun addUser(@Body user: User):Call<ResponseBody>
    @POST("/workout_Plans")
    suspend fun addPlan(@Body plan: Workout):Call<ResponseBody>
//delete a plan
    @DELETE("workout_Plans/{id}")
    fun deletePlan(@Path("id") id: String?):Call<ResponseBody>

    @PUT("workout_Plans/{id}")
    fun updateUser(@Path("id") id: String?, @Body user: User?)


}
