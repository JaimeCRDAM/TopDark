package models.data_classes.ship

import java.lang.reflect.Type
import kotlin.properties.Delegates


enum class ShipsEnum {
    Fighters("Fighters", false, models.data_classes.ship.Fighters::class.java,com.example.topdark.R.drawable.fighters,models.data_classes.ship.Fighters::class.java),
    Bombers("Bombers", true, models.data_classes.ship.Bombers::class.java,com.example.topdark.R.drawable.bombers,models.data_classes.ship.Bombers::class.java),
    Shuttles("Shuttles", true, models.data_classes.ship.Shuttles::class.java, com.example.topdark.R.drawable.shuttles,models.data_classes.ship.Shuttles::class.java);

    lateinit var shipName: String
    var cargo by Delegates.notNull<Boolean>()
    lateinit var dataClass: Class<*>
    var avatar: Int? = null
    lateinit var klassType: Type

    constructor()

    constructor(
        shipName: String,
        cargo: Boolean,
        dataClass: Class<*>,
        avatar: Int,
        klassType : Type
    ) {
        this.shipName = shipName
        this.cargo = cargo
        this.dataClass = dataClass
        this.avatar = avatar
        this.klassType = klassType
    }
}