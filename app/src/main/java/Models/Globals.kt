package Models

import Models.dataclasses.userresponse.LoggedInUser
import services.UserServicesImpl

object Globals {
    val userServices = UserServicesImpl()
    var user: LoggedInUser? = null

}