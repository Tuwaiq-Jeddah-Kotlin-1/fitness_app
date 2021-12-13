package com.tuwaiq.fitnessapp.Auth

interface Response {
    fun success(msg:String)
    fun fail(msg:String)
}