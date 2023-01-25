package com.example.topdark.data

import models.Globals
import models.data_classes.UserCredentials
import android.os.Build
import androidx.annotation.RequiresApi
import models.data_classes.user_login_response.LoggedInUser
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
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Success(user)
    }

    fun logout() {
        // TODO: revoke authentication
    }
}