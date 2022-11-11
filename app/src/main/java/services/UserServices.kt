package services

import Models.dataclasses.UserCredentials

interface UserServices {

    fun logInWithCredentials(userCredentials: UserCredentials)


}