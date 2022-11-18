package models.data_classes.to_present

data class Mission(
    val _id: String,
    val amount: Int,
    val firstCheck: Boolean,
    val secondCheck: Boolean,
    val missionType: String
)