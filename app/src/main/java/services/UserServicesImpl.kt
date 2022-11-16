package services

import Models.Globals
import Models.dataclasses.UserCredentials
import Models.dataclasses.missionresponse.MissionRequestResult
import Models.dataclasses.missions.GeneralDataClass
import Models.dataclasses.userresponse.ResponseData
import Requests.ServerRequests
import android.os.Build
import androidx.annotation.RequiresApi
import Models.dataclasses.userresponse.LoggedInUser
import httpmethods.RetrofitClient
import httpmethods.RetrofitClientImpl
import retrofit2.Response
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
        val al: Response<ResponseData> = retrofitclient.create(ServerRequests::class.java).LoginWithCredentials()
        if (al.body() != null){
            val userId = al.body()?.data?.username!!
            val displayName = al.body()?.data?.nickname!!
            val authToken = al.body()?.data?.authToken!!
            return LoggedInUser(userId, displayName, authToken)
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
}