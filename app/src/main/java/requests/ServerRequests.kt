package requests

import Models.dataclasses.user.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface ServerRequests {
    @GET("/auth/userpassword")
    suspend fun LoginWithCredentials(): Response<ResponseData>

    @GET("/auth/jwtAuth")
    fun LoginWithJwt()
}