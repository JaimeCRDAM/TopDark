package Models.dataclasses.missions

import com.example.topdark.R
import java.lang.reflect.Constructor
import java.lang.reflect.Type

enum class MissionsEnum{
    Flight("Flight", Models.dataclasses.missions.Flight::class.java, R.id.GFlight, R.string.flight_duration, Models.dataclasses.missions.Flight::class.java),
    Combat("Combat", Models.dataclasses.missions.Combat::class.java,R.id.GCombat, R.string.targets, Models.dataclasses.missions.Combat::class.java),
    Bombardment("Bombardment", Models.dataclasses.missions.Bombardment::class.java,R.id.GBombardment, R.string.enemies, Models.dataclasses.missions.Bombardment::class.java);


    lateinit var missionClass: String
    lateinit var dataClass: Class<*>
    var group: Int = 0
    var hint: Int = 0
    lateinit var klassType: Type

    constructor()

    constructor(
        missionClass: String,
        dataClass: Class<*>,
        missionGroup: Int,
        hint: Int,
        klassType : Type
    ) {
        this.missionClass = missionClass
        this.dataClass = dataClass
        this.group = missionGroup
        this.hint = hint
        this.klassType = klassType
    }
}
