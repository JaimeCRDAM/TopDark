package com.example.topdark.vaderpages.fragments.register_mission.data

import models.Globals
import models.data_classes.mission_register_response.MissionRequestResult
import models.data_classes.missions.GeneralDataClass
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.topdark.data.Result
import java.io.IOException

class RegisterMissionDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(missionData: GeneralDataClass, missionClass: String): Result<MissionRequestResult> {
        var mission: MissionRequestResult
        try {
            mission = Globals.userServices.registerMission(missionData, missionClass)!!

        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Success(mission)
    }
}