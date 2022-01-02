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
import retrofit2.http.PUT










interface FitnessApi {

    @GET("users")
    suspend fun getUser(@Query("email") email:String ):List<User>

    @GET("users")
    suspend fun getUserById(@Query("email") email:String ):List<User>
    @GET("/exercise")
    suspend fun getExercises(@Query("category") category:String? ):Response<List<Exercise>>

    @GET("/exercise")
    suspend fun getExercisesById(@Query("exercise_id") id:String? ):Response<List<Exercise>>

    @GET("/workout_Plans")
    suspend fun getPlan(@Query("user_id") user_id:String? ):Response<List<Workout>>

    @POST("users")
    suspend fun addUser(@Body user: User)
    @POST("/workout_Plans")
    suspend fun addPlan(@Body plan: Workout)
//delete a plan
    @DELETE("workout_Plans/{id}")
    suspend fun deletePlan(@Path("id") id: String?):Call<ResponseBody>

    @FormUrlEncoded
    @PUT("users/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Field("weight") weight: Number,
        @Field("height") height: Number,
        @Field("bmi") bmi: String,
    ):Response<User>

   // fun updateUser(@Path("id") id: String?, @Body user: User?):Call<ResponseBody>

    @FormUrlEncoded
    @PUT("users/{id}")
    suspend fun updateUserObject(
        @Path("id") id: String,
        @Field("name") name: String,
        @Field("weight") weight: Number,
        @Field("height") height: Number,
        @Field("bmi") bmi: String,): Response<User>
}
