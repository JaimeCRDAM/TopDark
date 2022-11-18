package com.example.topdark.vaderpages.fragments.register_pilot

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topdark.data.Result
import com.example.topdark.vaderpages.fragments.register_pilot.data.RegisterPilotDataSource
import models.data_classes.pilot_register_response.PilotRegisterRequestResult
import models.data_classes.pilot_register.RegisterPilotData

class RegisterPilotViewModel(val registerPilotDataSource: RegisterPilotDataSource): ViewModel() {
    private val _registerResult = MutableLiveData<RegisterPilotResult>()
    val registerResult: LiveData<RegisterPilotResult> = _registerResult

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun requestResult(pilotData: RegisterPilotData) {
        val result = request(pilotData)

        if (result is Result.Success) {
            _registerResult.postValue(RegisterPilotResult(result.data.message))
        } else {
            _registerResult.postValue(RegisterPilotResult(code = 0))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(pilotData: RegisterPilotData): Result<PilotRegisterRequestResult> {

        return registerPilotDataSource.request(pilotData)
    }
}