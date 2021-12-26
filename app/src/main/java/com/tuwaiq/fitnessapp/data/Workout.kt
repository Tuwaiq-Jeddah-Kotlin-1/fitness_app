package com.tuwaiq.fitnessapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Workout(val id:String="" ,val user_id:String="0",val title:String="",val exercise_id:Array<String> =arrayOf("")): Parcelable {
}