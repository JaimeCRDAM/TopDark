package services

import Models.dataclasses.UserCredentials
import Models.dataclasses.missionresponse.MissionRequestResult
import Models.dataclasses.missions.GeneralDataClass
import Models.dataclasses.userresponse.LoggedInUser

interface UserServices {

    suspend fun logInWithCredentials(userCredentials: UserCredentials): LoggedInUser?

    suspend fun registerMission(klass: GeneralDataClass, missionClass: String): MissionRequestResult?



}