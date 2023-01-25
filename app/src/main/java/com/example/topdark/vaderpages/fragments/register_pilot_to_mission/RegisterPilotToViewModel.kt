package com.example.topdark.vaderpages.fragments.register_pilot_to_mission

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topdark.data.Result
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.data.RegisterPilotToSource
import models.data_classes.PilotToMission

class RegisterPilotToViewModel(
    val registerShipDataSource: RegisterPilotToSource
): ViewModel() {
    private val _registerResult = MutableLiveData<RegisterPilotToResult>()
    val registerPilotToResult: LiveData<RegisterPilotToResult> = _registerResult

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun requestResult(pilotToMission: PilotToMission) {
        // can be launched in a separate asynchronous job
        val result = request(pilotToMission)

        if (result is Result.Success) {
            _registerResult.postValue(RegisterPilotToResult(result.data.message))
        } else {
            _registerResult.postValue(RegisterPilotToResult(code = 0))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(pilotToMission: PilotToMission): Result<RegisterPilotToResult> {

        return registerShipDataSource.request(pilotToMission)
    }
}