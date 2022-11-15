package Models.dataclasses.ship



enum class ShipsEnum {
    Fighters("Fighters", false, com.example.topdark.R.drawable.fighters),
    Bombers("Bombers", true, com.example.topdark.R.drawable.bombers),
    Shuttles("Shuttles", true, com.example.topdark.R.drawable.shuttles);

    var shipName: String? = null
    var cargo : Boolean? = null
    var avatar: Int? = null

    constructor()

    constructor(
        shipName: String?,
        cargo: Boolean?,
        avatar: Int
    ) {
        this.shipName = shipName
        this.cargo = cargo
        this.avatar = avatar
    }
}