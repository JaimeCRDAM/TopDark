package models.data_classes.to_present

import android.graphics.Bitmap

data class Ship(
    val plate: String,
    val type: String,
    val avatar: Bitmap,
    val firstCheck: Boolean, //Can cargo
    val secondCheck: Boolean //Can passengers
)
