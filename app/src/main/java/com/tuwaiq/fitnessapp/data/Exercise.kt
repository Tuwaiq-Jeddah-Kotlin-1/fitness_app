package com.tuwaiq.fitnessapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Exercise(
    var exercise_id: String,
    var exercise_name: String,
    var category: String,
    var image_url:String,
    var isSelected:Boolean=false
): Parcelable
