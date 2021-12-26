package com.tuwaiq.fitnessapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.LocaleData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.tuwaiq.fitnessapp.MainActivity
import com.tuwaiq.fitnessapp.R
import com.tuwaiq.fitnessapp.VM_profile
import com.tuwaiq.fitnessapp.data.User
import com.tuwaiq.fitnessapp.databinding.FragmentUserProfileBinding
import com.tuwaiq.fitnessapp.notification.NotSchedualer
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.time.temporal.TemporalQueries.localDate





class UserProfile : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    lateinit var UserPrefrence: SharedPreferences
    val auth = FirebaseAuth.getInstance()
    private var backPressedTime:Long = 0
    lateinit var backToast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    companion object {
        fun newInstance() = UserProfile()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[VM_profile::class.java]
       // binding.lifecycleOwner = this
        // viewModel.getUser_()
        UserPrefrence = requireContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = UserPrefrence.getString("userObj", "")
        val userObj = gson.fromJson(json, User::class.java)


        binding.vmProfile = userObj
        viewModel.user.observe(viewLifecycleOwner,{
             binding.vmProfile = it.first()
         })

       // Log.e("user profile fragment ", viewModel.user.toString())
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.action_userProfile_to_login)

        }

        var selectedDate:String=""
        var cal = Calendar.getInstance()
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "yyyy/MM/dd" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                selectedDate=sdf.format(cal.time)
                binding.SelectedDate.text=selectedDate
                val localDate=LocalDate.now()
                val formattedLocalDate =convertDateToString(localDate)
                Log.e("----------------$formattedLocalDate","$selectedDate")
                if(formattedLocalDate == selectedDate){
                    NotSchedualer().setUpnotification(MainActivity())
                }
            }
        }
        Log.e("----------------1:PLEASE SHOW ME WHAT HAPPENED","$selectedDate")

        binding.selectDate.setOnClickListener {
            DatePickerDialog(requireContext(),
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

    }
    fun convertDateToString(Date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        val formattedCreationDate: String = Date.format(formatter)
        return formattedCreationDate
    }


}
