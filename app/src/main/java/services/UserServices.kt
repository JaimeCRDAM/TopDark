package services

import com.example.topdark.vaderpages.fragments.register_chip.RegisterShipResult
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.RegisterPilotToResult
import models.data_classes.PilotToMission
import models.data_classes.UserCredentials
import models.data_classes.all_ships_response.AllShipsResponse
import models.data_classes.idk.AllData
import models.data_classes.idk.Data
import models.data_classes.mission_register_response.MissionRequestResult
import models.data_classes.missions.GeneralDataClass
import models.data_classes.pilot_register_response.PilotRegisterRequestResult
import models.data_classes.pilot_register.RegisterPilotData
import models.data_classes.ship.GeneralShipDataClass
import models.data_classes.to_present.Mission
import models.data_classes.to_present.Pilot
import models.data_classes.to_present.Ship
import models.data_classes.user_login_response.LoggedInUser
import retrofit2.Response
import retrofit2.http.Path

interface UserServices {

    suspend fun logInWithCredentials(userCredentials: UserCredentials): LoggedInUser?

    suspend fun registerMission(
        klass: GeneralDataClass,
        missionClass: String
    ): MissionRequestResult?

    suspend fun registerPilot(pilot: RegisterPilotData): PilotRegisterRequestResult?

    suspend fun registerShip(missionData: GeneralShipDataClass): RegisterShipResult?

    suspend fun getAllPilots(): MutableList<Pilot>?

    suspend fun getAllMissions(): MutableList<Mission>?

    suspend fun getAllShips(): MutableList<Ship>?

    suspend fun registerPilotToMission(pilotToMission: PilotToMission): RegisterPilotToResult?

    suspend fun getAlldata(key: String): MutableList<Data>??

}