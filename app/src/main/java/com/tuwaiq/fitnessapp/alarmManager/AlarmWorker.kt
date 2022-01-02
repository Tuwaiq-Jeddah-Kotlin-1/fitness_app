package com.tuwaiq.fitnessapp.alarmManager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AlarmWorker(val context: Context, workparams: WorkerParameters) :
    Worker(context, workparams) {
    override fun doWork(): Result {


        return Result.success()
    }

}

