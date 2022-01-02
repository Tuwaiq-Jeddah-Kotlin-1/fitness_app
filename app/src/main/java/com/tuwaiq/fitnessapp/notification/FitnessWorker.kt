package com.tuwaiq.fitnessapp.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.tuwaiq.fitnessapp.MainActivity
import kotlinx.coroutines.flow.callbackFlow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.time.DurationUnit

const val NOTIFICATION_CHANNEL_ID = "appName_channel_01"

class FitnessWorker(val context: Context, workparams: WorkerParameters) :
    Worker(context, workparams) {


    override fun doWork(): Result {
        //notification


        val name = " workout1 notification"
        val intent = Intent(context, MainActivity::class.java)
        //pendingActivity in case the app was closed
        val pendingActivity = PendingIntent.getActivity(context, 0, intent, 0)

        val notification = NotificationCompat
            .Builder(context, "NOTIFICATION_CHANNEL_ID")
            .setTicker(name)
            .setSmallIcon(android.R.drawable.star_on)
            .setContentTitle(name)
            .setContentIntent(pendingActivity)
            .setAutoCancel(true)
            .setContentText("testing worker")
            .build()


        val notification_manager = NotificationManagerCompat
            .from(context)

        notification_manager.notify(0, notification)

        return Result.success()
    }


}