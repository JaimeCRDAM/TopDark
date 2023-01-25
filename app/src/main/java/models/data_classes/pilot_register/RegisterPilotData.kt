package models.data_classes.pilot_register

data class RegisterPilotData(
    val userName: String,
    val nickName: String = userName,
    val password: String,
    val avatar: String,
    val experience: Int,
    val age: Int
)
