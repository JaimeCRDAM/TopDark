package com.example.topdark.vaderpages.fragments.register_pilot_to_mission.data

import models.Globals
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.topdark.data.Result
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.RegisterPilotToResult
import models.data_classes.PilotToMission
import java.io.IOException

class RegisterPilotToSource {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(pilotToMission: PilotToMission): Result<RegisterPilotToResult> {
        val result: RegisterPilotToResult
        try {
            result = Globals.userServices.registerPilotToMission(pilotToMission)!!

        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Success(result)
    }
}