package Models

import com.example.topdark.data.model.LoggedInUser
import services.UserServicesImpl

object Globals {
    val userServices = UserServicesImpl()
    var user: LoggedInUser? = null

}