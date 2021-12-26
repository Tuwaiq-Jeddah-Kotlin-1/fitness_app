package com.tuwaiq.fitnessapp.notification

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.work.*
import com.tuwaiq.fitnessapp.MainActivity
import java.util.concurrent.TimeUnit

class NotSchedualer() {
    fun setUpnotification(mainActivity: MainActivity) {
        // val stkwrok = StockUpdateWork()
        val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val oneTimeWorker = OneTimeWorkRequest
            .Builder(FitnessWorker::class.java)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(mainActivity).enqueue(oneTimeWorker)

    }

}