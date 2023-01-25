package com.example.topdark.vaderpages.fragments.register_chip

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topdark.data.Result
import com.example.topdark.vaderpages.fragments.register_chip.data.RegisterShipDataSource
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.data.RegisterPilotToSource
import models.data_classes.PilotToMission
import models.data_classes.ship.GeneralShipDataClass

class RegisterShipViewModel(
    val registerShipDataSource: RegisterShipDataSource
): ViewModel() {
    private val _registerResult = MutableLiveData<RegisterShipResult>()
    val registerShipResult: LiveData<RegisterShipResult> = _registerResult

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun requestResult(generalShipDataClass: GeneralShipDataClass) {
        // can be launched in a separate asynchronous job
        val result = request(generalShipDataClass)

        if (result is Result.Success) {
            _registerResult.postValue(RegisterShipResult(result.data.message))
        } else {
            _registerResult.postValue(RegisterShipResult(code = 0))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(generalShipDataClass: GeneralShipDataClass): Result<RegisterShipResult> {

        return registerShipDataSource.request(generalShipDataClass)
    }
}