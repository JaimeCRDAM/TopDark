package com.example.topdark.vaderpages.fragments.registermission

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topdark.R
import com.example.topdark.data.Result
import com.example.topdark.ui.login.LoggedInUserView
import com.example.topdark.ui.login.LoginFormState
import com.example.topdark.ui.login.LoginResult

class RegisterViewModel: ViewModel() {
    private val _registerResult = MutableLiveData<LoginResult>()
    val registerResult: LiveData<LoginResult> = _registerResult

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun login(username: String, password: String): Boolean {
        // can be launched in a separate asynchronous job
        //val result = loginRepository.login(username, password)

        /*if (result is Result.Success) {
            _loginResult.postValue(LoginResult(success = LoggedInUserView(displayName = result.data.displayName)))

            return true
        } else {
            _loginResult.postValue(LoginResult(error = R.string.login_failed))
            return false
        }*/
        return false
    }
}