package models.data_classes.ship

data class GeneralShipDataClass(
    val plate: String,
    val type: String,
    val avatar: String,
    val firstCheck: Boolean, //Can cargo
    val secondCheck: Boolean //Can passengers
)