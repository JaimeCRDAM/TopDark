package Models

import Models.dataclasses.user.User
import services.UserServicesImpl

object Globals {
    val UserServices = UserServicesImpl()

    var currentUser:User? = null

}