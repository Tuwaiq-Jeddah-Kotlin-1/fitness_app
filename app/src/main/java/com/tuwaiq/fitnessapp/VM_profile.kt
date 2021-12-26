package com.tuwaiq.fitnessapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tuwaiq.fitnessapp.Api.FitnessRepo
import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.data.User
import kotlinx.coroutines.launch

class VM_profile():ViewModel() {
     var user=MutableLiveData<List<User>>()
    val repo =FitnessRepo()
    val firebase_repo=Firebase_repo()

    fun getUser_():MutableLiveData<List<User>>{
        viewModelScope.launch{
            user.postValue(repo.getUser())
         //  Log.e("user profile fragment ",user.value?.first()?.name!!)
        }
        return user
    }
   /* fun logOut(){
        viewModelScope.launch {
            firebase_repo.repo_logOut()
        }
    }*/
}