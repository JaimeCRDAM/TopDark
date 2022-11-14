package services

import Models.dataclasses.UserCredentials
import com.example.topdark.data.model.LoggedInUser

interface UserServices {

    suspend fun logInWithCredentials(userCredentials: UserCredentials): LoggedInUser?


}