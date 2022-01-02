package com.tuwaiq.fitnessapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.tuwaiq.fitnessapp.Api.FitnessRepo
import com.tuwaiq.fitnessapp.Auth.Firebase_repo
import com.tuwaiq.fitnessapp.Auth.Response
import com.tuwaiq.fitnessapp.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VM_profile() : ViewModel() {
    var user = MutableLiveData<User>()
    val firebase_repo = Firebase_repo()
    val repo = FitnessRepo()
    var height = MutableLiveData<String>()
    var name = MutableLiveData<String>()

    var weight = MutableLiveData<String>()
    var bmi = MutableLiveData<String>()

    var response: Response? = null

    fun getUser(){
        viewModelScope.launch {
            user.postValue(repo.getUser().first())
        }
    }


    fun logOut(nav: NavController) {
        viewModelScope.launch {
            firebase_repo.repo_logOut(nav)
        }
    }

    fun AdditionalupdateUser(id: String): Boolean {
        var result: Boolean = true
        viewModelScope.launch {
            if (checkNullOrEmpty(arrayListOf(height.value, weight.value))) {
                //    Log.e("user view model",updateUser.toString())
                val bmi = calculateBMI(height.value!!.toInt(), weight.value!!.toInt())
                if (repo.updateUser(
                        id,
                        weight.value!!.toInt(),
                        height.value!!.toInt(),
                        bmi
                    ).isSuccessful
                ) {
                    Log.e("success", "the response succeed")

                    result = true
                    response?.success("additional Info successfully added")
                } else {
                    Log.e("fail", "the response not successfully done")
                    result = false
                    response?.success("failed to add the additional information")
                }

                //   repo.updateUser(updateUser)
            } else {
                Log.e("empty", "empty ")
                result = false
                response?.fail("You have to fill the boxes")
            }

        }
        return result
    }

    fun updateUser(id: String) {
        viewModelScope.launch {
            var bmi = ""
            if (user.value!!.height!!.toInt() > 0 && user.value!!.weight!!.toInt() > 0) {
                bmi = calculateBMI(user.value!!.height!!.toInt() , user.value!!.weight!!.toInt())
            } else {
                bmi = "nothing to show"
            }

            val res = repo.updateUserObj(
                id, user.value!!.name, user.value!!.weight!!.toInt(),
                user.value!!.height!!.toInt(),
                bmi
            )
            if (res.isSuccessful) {
                user.postValue(res.body())
                Log.e("update user object viewModel", res.toString())
            } else {
                Log.e("update user object viewModel", res.toString())
            }
        }
    }

    private fun calculateBMI(height: Int, weight: Int): String {
        //41/(157^2)*10000
        Log.e("calculate",height.toString()+weight.toString())
        val bmi: Int = (10000*weight / (height * height))
        Log.e("BMI",((10000*weight / (height * height))).toString())
        return when {
            bmi <= 18 -> "$bmi -Under weight"
            bmi in 19..24 -> "$bmi -Normal"
            bmi in 25..29 -> "$bmi -Over weight"
            bmi in 30..34 -> "$bmi -Obese"
            else -> "$bmi -Extremely obese"
        }

    }

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