package services

import Models.Globals
import Models.dataclasses.UserCredentials
import Models.dataclasses.user.ResponseData
import Requests.ServerRequests
import android.os.Build
import androidx.annotation.RequiresApi
import httpmethods.RetrofitClient
import httpmethods.RetrofitClientImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*

class UserServicesImpl : UserServices {
    var retrofit: RetrofitClient = RetrofitClientImpl()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun logInWithCredentials(userCredentials: UserCredentials) {
        val headerMap = hashMapOf<String, String>()
        headerMap["Authorization"] = "Basic ".plus(Base64.getEncoder().encodeToString("${userCredentials.username}:${userCredentials.password}".toByteArray()))
        val httpClientBuilder = this.retrofit.getClientWithHeaders(hashMap = headerMap)
        val httpClient = httpClientBuilder.build()
        val retrofitclient = retrofit.getRetrofit(httpClient)
        CoroutineScope(Dispatchers.IO).launch{
            val al: Response<ResponseData> = retrofitclient.create(ServerRequests::class.java).LoginWithCredentials()
            Globals.currentUser = al.body().data
        }
    }



}