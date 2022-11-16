package com.example.topdark.vaderpages.fragments.registermission

import Models.dataclasses.missionresponse.MissionRequestResult
import Models.dataclasses.missions.GeneralDataClass
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topdark.R
import com.example.topdark.data.Result
import com.example.topdark.vaderpages.fragments.registermission.data.RegisterMissionDataSource

class RegisterViewModel(val regiserMissionDataSource: RegisterMissionDataSource): ViewModel() {
    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun requestResult(klass: GeneralDataClass, missionClass: String) {
        // can be launched in a separate asynchronous job
        val result = request(klass,missionClass)

        if (result is Result.Success) {
            _registerResult.postValue(RegisterResult(success = ""))
        } else {
            _registerResult.postValue(RegisterResult(error = R.string.login_failed))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(klass: GeneralDataClass, missionClass: String): Result<MissionRequestResult> {
        // handle login

        return regiserMissionDataSource.request(klass, missionClass)
    }
}