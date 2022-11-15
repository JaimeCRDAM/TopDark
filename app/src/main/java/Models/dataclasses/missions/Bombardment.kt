package Models.dataclasses.missions

data class Bombardment(
    val targets: Int = 0,
    val cargo: Boolean = false,
    val passengers: Boolean = false
)
