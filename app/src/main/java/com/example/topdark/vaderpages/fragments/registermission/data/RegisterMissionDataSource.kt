package com.example.topdark.vaderpages.fragments.registermission.data

import Models.Globals
import Models.dataclasses.missionresponse.MissionRequestResult
import Models.dataclasses.missions.GeneralDataClass
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.topdark.data.Result
import java.io.IOException

class RegisterMissionDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(klass: GeneralDataClass, missionClass: String): Result<MissionRequestResult> {
        var user: MissionRequestResult
        try {
            user = Globals.userServices.registerMission(klass, missionClass)!!

        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Success(user)
    }
}