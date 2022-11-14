package services

import Models.Globals
import Models.dataclasses.UserCredentials
import Models.dataclasses.user.ResponseData
import android.os.Build
import androidx.annotation.RequiresApi
import httpmethods.RetrofitClient
import httpmethods.RetrofitClientImpl
import requests.ServerRequests
import retrofit2.Response
import java.util.*

class UserServicesImpl : UserServices {
    var retrofit: RetrofitClient = RetrofitClientImpl()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun logInWithCredentials(userCredentials: UserCredentials): Boolean {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Basic ".plus(Base64.getEncoder().encodeToString("${userCredentials.username}:${userCredentials.password}".toByteArray()))
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitClient = retrofit.getRetrofit(httpClient)
        val response: Response<ResponseData> = retrofitClient.create(ServerRequests::class.java).LoginWithCredentials()
        val message: String?
        if (response.body() != null) {
            Globals.currentUser = response.body()?.data
        } else {
            Globals.currentUser = null
        }
        return Globals.currentUser != null
    }
}