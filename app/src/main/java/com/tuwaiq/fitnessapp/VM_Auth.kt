package com.tuwaiq.fitnessapp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.tuwaiq.fitnessapp.Api.FitnessRepo
import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.Auth.Response
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.ui.LoginDirections
import com.tuwaiq.fitnessapp.ui.SignUpDirections
import kotlinx.coroutines.*

class VM_Auth() : ViewModel() {
    var name= MutableLiveData<String>()
    var email= MutableLiveData<String>()
    var age= MutableLiveData<String>()
    var height= MutableLiveData<String>()
    var weight= MutableLiveData<String>()
    var gender= MutableLiveData<String>()
    var password= MutableLiveData<String>()
    val fitnessRepo = FitnessRepo()
    val repo = Firebase_repo()
    var response:Response?=null
    var auth: FirebaseAuth = FirebaseAuth.getInstance()


    fun logIn(nav:NavController){

        viewModelScope.launch{
            if (checkNullOrEmpty(arrayListOf(email.value, password.value))) {
                repo.repo_logIn(email.value!!, password.value!!, nav)
                response?.success("success logIn")
            } else {
                //fail
                response?.fail("fail sign in")

            }
        }
    }//end of login fun

    fun signUp(nav:NavController) {
        viewModelScope.launch {
            if (checkNullOrEmpty(arrayListOf(name.value, email.value, age.value,
                        gender.value, weight.value, height.value, password.value))) {
               repo.repo_signUp(email.value!!, password.value!!,nav)
                    response?.success("success sign up")
                    // user object
                        val user =User("",name.value!!, age.value!!.toInt(),
                            email.value!!, gender.value!!,weight.value!!.toDouble(),height.value!!.toInt())
                   withContext(Dispatchers.IO){
                       fitnessRepo.addUser(user)
                   }//add the user
            } else {
                response?.fail("fail sign up")
            }
        }
        }// end of signUp fun


    private fun checkNullOrEmpty(inputs: ArrayList<String?>): Boolean {
        var result = true
        for (i in inputs) {
            if (i.isNullOrEmpty()) {
                result = false
                break
            } else {
                result = true
            }
        }
        return result
    }//end of checkNullOrEmpty
}
