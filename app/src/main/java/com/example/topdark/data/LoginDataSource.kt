package com.example.topdark.data

import Models.Globals
import Models.dataclasses.UserCredentials
import android.os.Build
import androidx.annotation.RequiresApi
import Models.dataclasses.userresponse.LoggedInUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        var user: LoggedInUser? = null
        try {
            val userCredentials = UserCredentials(username, password)
            user = Globals.userServices.logInWithCredentials(userCredentials)!!

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Success(user)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}