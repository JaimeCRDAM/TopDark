package com.example.topdark.vaderpages.fragments.register_chip.data

import models.Globals
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.topdark.data.Result
import com.example.topdark.vaderpages.fragments.register_chip.RegisterShip
import com.example.topdark.vaderpages.fragments.register_chip.RegisterShipResult
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.RegisterPilotToResult
import models.data_classes.PilotToMission
import models.data_classes.ship.GeneralShipDataClass
import java.io.IOException

class RegisterShipDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun request(generalShipDataClass: GeneralShipDataClass): Result<RegisterShipResult> {
        val result: RegisterShipResult
        try {
            result = Globals.userServices.registerShip(generalShipDataClass)!!

        } catch (e: Throwable) {
            e.printStackTrace()
            return Result.Error(IOException("Error logging in", e))
        }

        return Result.Success(result)
    }
}