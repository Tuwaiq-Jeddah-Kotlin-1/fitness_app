package com.tuwaiq.fitnessapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String="",
    var name: String,
    var email: String,
    var gender: String,
    var weight: Double?=0.0,
    var height: Int?=0,
    var bmi:String=""
): Parcelable