package com.tuwaiq.fitnessapp.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class FitnessApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name =" workout notification"
            val importantce = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("NOTIFICATION_CHANNEL_ID",name,importantce)
            val notification_manager: NotificationManager =
                getSystemService(NotificationManager ::class.java)
            notification_manager.createNotificationChannel(channel)
        }
    }
}