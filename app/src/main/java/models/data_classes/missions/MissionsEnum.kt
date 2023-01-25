package models.data_classes.missions

import com.example.topdark.R
import java.lang.reflect.Type

enum class MissionsEnum{
    Flight("Flight", models.data_classes.missions.Flight::class.java, R.id.GFlight, R.string.flight_duration, models.data_classes.missions.Flight::class.java),
    Combat("Combat", models.data_classes.missions.Combat::class.java,R.id.GCombat, R.string.targets, models.data_classes.missions.Combat::class.java),
    Bombardment("Bombardment", models.data_classes.missions.Bombardment::class.java,R.id.GBombardment, R.string.enemies, models.data_classes.missions.Bombardment::class.java);


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
