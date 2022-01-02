package com.tuwaiq.fitnessapp.notification

import android.content.Context
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.tuwaiq.fitnessapp.MainActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class NotSchedualer() {
    fun setUpnotification(mainActivity: MainActivity,shared:SharedPreferences) {
        // val stkwrok = StockUpdateWork()
     /*   val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()*/
        val selectedDates: MutableSet<String> = mutableSetOf()
        shared.getStringSet("dates", selectedDates)
            val dates = convertStringToCalendar(selectedDates.toMutableList())
            val now = LocalDateTime.now()
            val calendar = Calendar.getInstance()
            calendar.set(
                now.year,
                now.monthValue - 1,
                now.dayOfMonth,
                now.hour,
                now.minute,
                now.second
            )
        val deudate = Calendar.getInstance()
        calendar.set(
            now.year,
            now.monthValue - 1,
            now.dayOfMonth,
            now.hour,
            now.minute,
            now.second
        )
            Log.e("today",calendar.toString())
          for (selceted in dates) {
                val timeDiff = selceted.timeInMillis-calendar.timeInMillis
                val alarm = OneTimeWorkRequestBuilder<FitnessWorker>()
                    .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                    .build()
                WorkManager.getInstance(mainActivity)
                    .enqueue(alarm)

        }
    }

    private fun convertStringToCalendar(date: MutableList<String>): MutableList<Calendar> {
        val strTocalendar = mutableListOf<Calendar>()
        for (i in date) {
            val arr = i.split("/")
            var date = Calendar.getInstance()
            date.set(Calendar.DAY_OF_MONTH, arr[2].toInt());
            date.set(Calendar.HOUR, 9);
            date.set(Calendar.MINUTE, 17);
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
            date.set(Calendar.MONTH, arr[1].toInt());
            date.set(Calendar.YEAR, arr[0].toInt());
            strTocalendar.add(date)

        }
        return strTocalendar
    }


}