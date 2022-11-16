package Models.dataclasses.missions

import java.lang.reflect.Constructor

enum class MissionsEnum{
    Flight("Flight", Models.dataclasses.missions.Flight::class.java),
    Combat("Combat", Models.dataclasses.missions.Combat::class.java),
    Bombardment("Bombardment", Models.dataclasses.missions.Bombardment::class.java);


    var missionClass: String? = null
    lateinit var dataClass: Constructor<*>

    constructor()

    constructor(
        missionClass: String?,
        dataClass: Class<*>
    ) {
        this.missionClass = missionClass
        this.dataClass = dataClass.declaredConstructors[1]
    }

}
