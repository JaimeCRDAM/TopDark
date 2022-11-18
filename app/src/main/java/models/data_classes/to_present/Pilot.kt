package models.data_classes.to_present

import android.graphics.Bitmap

data class Pilot(
    val _id: String,
    val avatar: Bitmap,
    val name: String,
    val age: Int,
    val success: Double,
    val experience: Int
)
