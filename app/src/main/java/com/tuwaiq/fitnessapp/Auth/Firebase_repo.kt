package com.tuwaiq.fitnessapp.Auth

import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth

class Firebase_repo {
    val auth = FirebaseAuth.getInstance()
    private var firebaseUserId: String = ""
    var response:Response?=null

    fun repo_signUp(email:String,password:String):Boolean{
        var next=false
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("Sign up", "................TASK SUCCEED.................")
                    firebaseUserId = auth.currentUser!!.uid
                    next=true
            } else {
                    response?.fail("Error Message: " + task.exception!!.message.toString())
                }
            }
        return next
    }

    fun repo_logIn(email:String,password:String):Boolean{
        var next=false
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.e("***********LOG IN","*******SUCCEED")
                    next=true

                } else {
                    response?.fail("Error Message: " + task.exception!!.message.toString())
                }
            }
        return next
    }
}