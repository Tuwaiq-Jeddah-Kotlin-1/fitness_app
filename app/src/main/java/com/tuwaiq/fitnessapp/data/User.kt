package com.tuwaiq.fitnessapp.data
data class users(val users:List<User>)

data class User(
    var id: String,
    var name: String,
    var age: Int,
    var email: String,
    var gender: String,
    var weight: Double,
    var height: Int
)