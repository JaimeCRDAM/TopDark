package models

import models.data_classes.to_present.Mission
import models.data_classes.user_login_response.LoggedInUser
import retrofit2.converter.jackson.JacksonConverterFactory
import services.UserServicesImpl

object Globals {
    val userServices = UserServicesImpl()
    var user: LoggedInUser? = null

    lateinit var selectedMission: Mission

}