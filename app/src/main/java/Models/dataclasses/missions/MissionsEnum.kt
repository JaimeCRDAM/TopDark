package Models.dataclasses.missions

enum class MissionsEnum{
    Flight("Flight"),
    Combat("Combat"),
    Bombardment("Bombardment");


    var missionClass: String? = null

    constructor()

    constructor(
        missionClass: String?,
    ) {
        this.missionClass = missionClass
    }


}
