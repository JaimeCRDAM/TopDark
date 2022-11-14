package services

import Models.dataclasses.UserCredentials

interface UserServices {

    suspend fun logInWithCredentials(userCredentials: UserCredentials): Boolean


}