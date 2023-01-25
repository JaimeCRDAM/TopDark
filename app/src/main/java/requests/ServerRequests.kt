package requests

import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.RegisterPilotToResult
import models.data_classes.PilotToMission
import models.data_classes.all_missions_response.AllMissionsResponse
import models.data_classes.all_pilots_response.AllPilotsResponse
import models.data_classes.all_ships_response.AllShipsResponse
import models.data_classes.idk.AllData
import models.data_classes.idk.Data
import models.data_classes.missions.GeneralDataClass
import models.data_classes.pilot_register.RegisterPilotData
import models.data_classes.response_data.ResponseData
import models.data_classes.ship.GeneralShipDataClass
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerRequests {
    @GET("/auth/userpassword")
    suspend fun loginWithCredentials(): Response<ResponseData>

    @GET("/auth/pilots")
    suspend fun getAllPilots(): Response<AllPilotsResponse>

    @GET("/auth/missions")
    suspend fun getAllMissions(): Response<AllMissionsResponse>

    @GET("/auth/ships")
    suspend fun getAllShips(): Response<AllShipsResponse>

    @GET("/auth/allData/{id}")
    suspend fun getAlldata(@Path(value = "id") key: String): Response<AllData>

    @POST("/auth/register/mission/{id}")
    suspend fun registerMission(@Body klass: GeneralDataClass, @Path(value = "id") key: String): Response<ResponseData>

    @POST("/auth/register")
    suspend fun registerPilot(@Body pilot: RegisterPilotData): Response<ResponseData>

    @POST("/auth/register/ship")
    suspend fun registerShip(@Body ship: GeneralShipDataClass): Response<ResponseData>

    @POST("/auth/register/pilottomission")
    suspend fun registerPilotToMission(@Body pilotToMission: PilotToMission) : Response<RegisterPilotToResult>


}