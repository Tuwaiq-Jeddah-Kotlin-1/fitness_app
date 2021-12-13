package com.tuwaiq.fitnessapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FitnessBuilder {
    private const val BASE_URL="https://61b4daeb0e84b700173319bf.mockapi.io/"
    private fun retrofit(): Retrofit =Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val fitnessApi:FitnessApi = retrofit().create(FitnessApi::class.java)
}