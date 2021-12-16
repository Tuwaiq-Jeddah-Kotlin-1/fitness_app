package com.tuwaiq.fitnessapp.Auth

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.ui.LoginDirections
import com.tuwaiq.fitnessapp.ui.SignUp
import com.tuwaiq.fitnessapp.ui.SignUpDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Firebase_repo() {
    lateinit var auth:FirebaseAuth
    lateinit var firebaseUserEmail: String
    lateinit var firebaseUserid: String

    var response:Response?=null


    init{
    auth = FirebaseAuth.getInstance()
    firebaseUserEmail= auth.currentUser!!.email!!
        firebaseUserid=auth.currentUser!!.uid
        Log.e("user profile fragment ",firebaseUserEmail)

    }
    suspend fun repo_signUp(email:String,password:String,nav:NavController){
        withContext(Dispatchers.IO) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("Sign up", "................TASK SUCCEED.................")
                       // firebaseUserId = auth.currentUser!!.uid
                        val action = SignUpDirections.actionSignUpToLogin()
                        nav.navigate(action)
                        response?.success("sign up repo worked")

                    } else {
                        response?.fail("Error Message: " + task.exception!!.message.toString())
                    }
                }
        }
    }

    suspend fun repo_logIn(email:String,password:String,nav:NavController) {
        withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e("***********LOG IN", "*******SUCCEED")
                        response?.success("sign in repo worked")
                        val action = LoginDirections.actionLoginToMainScreen()
                        nav.navigate(action)
                    } else {
                        response?.fail("Error Message: " + task.exception!!.message.toString())
                    }
                }
        }
    }
}