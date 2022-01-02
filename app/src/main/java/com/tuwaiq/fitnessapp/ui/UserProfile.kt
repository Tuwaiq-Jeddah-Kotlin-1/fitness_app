package com.tuwaiq.fitnessapp.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import java.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.CalendarView
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
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.applandeo.materialcalendarview.utils.getMonthsToDate
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.collections.ArrayList
import kotlin.time.Duration.Companion.days


class UserProfile : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    lateinit var UserPrefrence: SharedPreferences
    val auth = FirebaseAuth.getInstance()
    private var backPressedTime: Long = 0
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


        viewModel.getUser()
      viewModel.user.observe(viewLifecycleOwner,{
        binding.user=it
        })
        if (userObj.gender == "Female") {

            binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.woman))
        } else {
            binding.profilepic.setImageDrawable(resources.getDrawable(R.drawable.ic_man_svgrepo_com))

        }
//        viewModel.user.observe(viewLifecycleOwner, {
//            binding.vmProfile = it.first()
//        })

        // Log.e("user profile fragment ", viewModel.user.toString())
        binding.btnLogout.setOnClickListener {
            //  auth.signOut()
            viewModel.logOut(findNavController())
            val UserPrefrence =
                requireContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            UserPrefrence.edit().clear().apply()

        }


        //expandable card view
        val editTextToHide = mutableListOf<TextInputEditText>(
            binding.profileName,
            binding.profileHeight, binding.profileWeight
        )
        binding.infoCard.setOnClickListener {
            if (binding.profileName.isGone) {
                binding.profileWeight.isVisible=true
                binding.profileHeight.isVisible=true
                binding.profileName.isVisible=true
                binding.profileSaveChanges.isVisible=true
            } else {
                binding.profileWeight.isGone=true
                binding.profileHeight.isGone=true
                binding.profileName.isGone=true
                binding.profileSaveChanges.isGone=true

            }

        }
        for (i in editTextToHide) {
            i.doOnTextChanged { text, start, before, count ->
              binding.profileSaveChanges.setBackgroundDrawable(resources.getDrawable(R.drawable.buttons_orange))

            }
        }
Log.e("profile user info",userObj.toString())
        binding.profileSaveChanges.setOnClickListener{
            viewModel.updateUser(userObj.id)
        }
        var lang=""
        binding.langwageImage.setOnClickListener{
           if(lang=="en"){
               lang="ar"
               setAppLocale(requireContext(),lang)
           }else{
               lang="en"
               setAppLocale(requireContext(),lang)
           }
        }

        // configuration of the calendar listener

        val notDates = requireContext().getSharedPreferences("selectedDates", Context.MODE_PRIVATE)
        val listener: OnSelectDateListener = object : OnSelectDateListener {
            override fun onSelect(calendar: List<Calendar>) {
                val selectedDates: MutableSet<String> = mutableSetOf()
                for (i in calendar) {
                    val date =
                        "${i.get(Calendar.YEAR)}/${i.get(Calendar.MONTH)}/${i.get(Calendar.DAY_OF_MONTH)}"
                    /*     i.set(Calendar.HOUR, 6)
                         i.set(Calendar.MINUTE, 0)
                         i.set(Calendar.SECOND, 0)
                         i.set(Calendar.MILLISECOND, 0)   */
                    selectedDates.add(date)
                    // binding.SelectedDate.text = i.get(Calendar.YEAR).toString()
                }
                notDates.edit().putStringSet("dates", selectedDates).apply()
                NotSchedualer().setUpnotification(MainActivity(), notDates)
                Log.e("dates", selectedDates.toString())
            }


        }

        val builder = DatePickerBuilder(requireContext(), listener)
            .pickerType(CalendarView.MANY_DAYS_PICKER)

        val datePicker: com.applandeo.materialcalendarview.DatePicker = builder.build()
        //get the calendar array

        val selectedDates: MutableSet<String> = mutableSetOf()
        notDates.getStringSet("dates", selectedDates)
        Log.e("shared dates", selectedDates.toString())
        binding.selectDate.setOnClickListener {
            datePicker.show()
        }

    }


    fun setAppLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }


}
