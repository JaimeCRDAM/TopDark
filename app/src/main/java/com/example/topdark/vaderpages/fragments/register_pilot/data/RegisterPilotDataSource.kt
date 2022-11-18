package com.example.topdark.vaderpages.fragments.register_pilot.data

import models.Globals
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.topdark.data.Result
import models.data_classes.pilot_register_response.PilotRegisterRequestResult
import models.data_classes.pilot_register.RegisterPilotData
import java.io.IOException

class RegisterPilotDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(pilotData: RegisterPilotData): Result<PilotRegisterRequestResult> {
        var pilot: PilotRegisterRequestResult
        try {
            pilot = Globals.userServices.registerPilot(pilotData)!!

        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Success(pilot)
    }
}