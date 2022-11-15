package services

import Models.dataclasses.UserCredentials
import Models.dataclasses.userresponse.LoggedInUser

interface UserServices {

    suspend fun logInWithCredentials(userCredentials: UserCredentials): LoggedInUser?


}