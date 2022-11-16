package Requests

import Models.dataclasses.missions.*
import Models.dataclasses.userresponse.ResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServerRequests {
    @GET("/auth/userpassword")
    suspend fun LoginWithCredentials(): Response<ResponseData>

    @GET("/auth/jwtAuth")
    fun LoginWithJwt()

    @POST("/auth/register/mission/{id}")
    suspend fun registerMission(@Body klass: GeneralDataClass, @Path(value = "id") key: String): Response<ResponseData>




}