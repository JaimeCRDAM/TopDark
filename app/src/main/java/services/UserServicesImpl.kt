package services

import android.graphics.BitmapFactory
import models.Globals
import models.data_classes.UserCredentials
import models.data_classes.mission_register_response.MissionRequestResult
import models.data_classes.missions.GeneralDataClass
import requests.ServerRequests
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.topdark.vaderpages.fragments.register_chip.RegisterShip
import com.example.topdark.vaderpages.fragments.register_chip.RegisterShipResult
import com.example.topdark.vaderpages.fragments.register_pilot_to_mission.RegisterPilotToResult
import http_methods.RetrofitClient
import http_methods.RetrofitClientImpl
import models.data_classes.PilotToMission
import models.data_classes.all_missions_response.AllMissionsResponse
import models.data_classes.all_pilots_response.AllPilotsResponse
import models.data_classes.all_ships_response.AllShipsResponse
import models.data_classes.idk.AllData
import models.data_classes.idk.Data
import models.data_classes.pilot_register_response.PilotRegisterRequestResult
import models.data_classes.pilot_register.RegisterPilotData
import models.data_classes.response_data.ResponseData
import models.data_classes.ship.GeneralShipDataClass
import models.data_classes.to_present.Mission
import models.data_classes.to_present.Pilot
import models.data_classes.to_present.Ship
import models.data_classes.user_login_response.LoggedInUser
import retrofit2.Response
import utils.ungzip
import java.net.HttpURLConnection
import java.util.*

class UserServicesImpl : UserServices {
    var retrofit: RetrofitClient = RetrofitClientImpl()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun logInWithCredentials(userCredentials: UserCredentials): LoggedInUser? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Basic ".plus(
            Base64.getEncoder().encodeToString(
                "${userCredentials.username}:${userCredentials.password}".toByteArray()
            )
        )
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<ResponseData> = retrofitclient.create(ServerRequests::class.java).loginWithCredentials()
        if (al.body() != null){
            val userId = al.body()?.data?.username!!
            val displayName = al.body()?.data?.nickname!!
            val authToken = al.body()?.data?.authToken!!
            val lastLogin = al.body()?.data?.lastLogin!!
            val id = al.body()?.data?.id!!
            return LoggedInUser(authToken, lastLogin, displayName, userId, id)
        }
        return null
    }

    override suspend fun registerMission(klass: GeneralDataClass, missionClass: String): MissionRequestResult? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<ResponseData> = retrofitclient.create(ServerRequests::class.java).registerMission(klass, missionClass)
        if (al.body() != null){
            val message = al.body()!!.message!!
            return MissionRequestResult(message)
        }
        return null
    }

    override suspend fun registerPilot(pilot: RegisterPilotData): PilotRegisterRequestResult? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<ResponseData> = retrofitclient.create(ServerRequests::class.java).registerPilot(pilot)
        if (al.body() != null){
            val code = al.code()
            val message = al.body()!!.message!!
            if (code == HttpURLConnection.HTTP_OK){
                return PilotRegisterRequestResult(message, code)
            }
            return PilotRegisterRequestResult(message, code)
        }
        return null
    }

    override suspend fun registerShip(missionData: GeneralShipDataClass): RegisterShipResult? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<ResponseData> = retrofitclient.create(ServerRequests::class.java).registerShip(missionData)
        if (al.body() != null){
            val code = al.code()
            val message = al.body()!!.message!!
            if (code == HttpURLConnection.HTTP_OK){
                return RegisterShipResult(message)
            }
            return RegisterShipResult(message, code)
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllPilots(): MutableList<Pilot>? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<AllPilotsResponse> = retrofitclient.create(ServerRequests::class.java).getAllPilots()
        if (al.body() != null){
            val code = al.code()
            val data = al.body()!!.data!!
            if (code == HttpURLConnection.HTTP_OK){
                val listToReturn = data.map {
                    val avatarToByteArray = Base64.getDecoder().decode(it?.avatar)
                    val bitmapData = Base64.getDecoder().decode(ungzip(avatarToByteArray))
                    val bitMap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.size);
                    Pilot(
                        it?._id!!,
                        bitMap,
                        it.nickName!!,
                        it.age!!,
                        it.success!!,
                        it.experience!!
                    )
                }.toMutableList()
                return listToReturn
            }
            return null
        }
        return null
    }

    override suspend fun getAllMissions(): MutableList<Mission>? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<AllMissionsResponse> = retrofitclient.create(ServerRequests::class.java).getAllMissions()
        if (al.body() != null){
            val code = al.code()
            val data = al.body()!!.data!!
            if (code == HttpURLConnection.HTTP_OK){
                val listToReturn = data.map {
                    Mission(
                        it._id!!,
                        it.amount!!,
                        it.firstCheck!!,
                        it.secondCheck!!,
                        it.missionType!!
                    )
                }.toMutableList()
                return listToReturn
            }
            return null
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllShips(): MutableList<Ship>? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<AllShipsResponse> = retrofitclient.create(ServerRequests::class.java).getAllShips()
        if (al.body() != null){
            val code = al.code()
            val data = al.body()!!.data!!
            if (code == HttpURLConnection.HTTP_OK){
                val listToReturn = data.map {
                    val imgByteArray = Base64.getDecoder().decode(ungzip(Base64.getDecoder().decode(it?.avatar)))
                    val bitMap = BitmapFactory.decodeByteArray(imgByteArray, 0, imgByteArray.size);
                    Ship(
                        it?.plate!!,
                        it.type!!,
                        bitMap,
                        it.firstCheck!!,
                        it.secondCheck!!
                    )
                }.toMutableList()
                return listToReturn
            }
            return null
        }
        return null
    }

    override suspend fun registerPilotToMission(pilotToMission: PilotToMission): RegisterPilotToResult? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<RegisterPilotToResult> = retrofitclient.create(ServerRequests::class.java).registerPilotToMission(pilotToMission)
        if (al.body() != null){
            val code = al.code()
            val message = al.body()!!.message!!
            if (code == HttpURLConnection.HTTP_OK){
                return RegisterPilotToResult(message)
            }
            return RegisterPilotToResult(message, code)
        }
        return null
    }

    override suspend fun getAlldata(key: String): MutableList<Data>? {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Bearer ".plus(Globals.user!!.authToken)
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        val al: Response<AllData> = retrofitclient.create(ServerRequests::class.java).getAlldata(key)
        val ak = mutableListOf<Data>()
        if (al.body() != null){
            val code = al.code()
            if (code == HttpURLConnection.HTTP_OK){
                al.body()!!.data?.forEach {
                    if (it != null) {
                        ak.add(Data(it.mission, it.ship))
                    }
                }

                return ak
            }
            return null
        }
        return null
    }
}