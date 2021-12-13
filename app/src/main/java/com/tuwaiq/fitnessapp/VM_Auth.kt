package com.tuwaiq.fitnessapp

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.Auth.Response

class VM_Auth() : ViewModel() {
    var name: MutableLiveData<String>?=null
    var age:MutableLiveData<String>?=null
    var email:MutableLiveData<String>?=null
    var gender:MutableLiveData<String>?=null
    var weight:MutableLiveData<String>?=null
    var height:MutableLiveData<String>? =null
    var password:MutableLiveData<String>? = null
    var response:Response?=null

    val repo = Firebase_repo()

    fun logIn(view: View):Boolean{
        var next=false
        if (checkNullOrEmpty(arrayListOf(email?.value,password?.value))) {
            response?.success("success logIn")
            if(repo.repo_logIn(email!!.value!!,password!!.value!!)){
                next=true
            }
        }else{
            //fail
        }
        return next
    }//end of login fun

fun signUp(view:View):Boolean{
    var next=false
    if(checkNullOrEmpty(arrayListOf(name?.value,email?.value,age?.value,gender?.value,weight?.value,height?.value,password?.value))){
      response?.success("success sign up")
      if(repo.repo_signUp(email!!.value!!,password!!.value!!)){
          next=true
          //create an account
//add the user to the api
      }
  }else{
      response?.fail("fail sign up")
  }
    return next
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
