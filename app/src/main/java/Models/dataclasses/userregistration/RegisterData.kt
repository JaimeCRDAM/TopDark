package Models.dataclasses.userregistration

import android.graphics.Bitmap

data class RegisterData(
    val userName: String,
    val nickName: String = userName,
    val password: String,
    val avatar: Bitmap,
    val experiencia: Int,
)
