package Requests

import Models.dataclasses.userresponse.ResponseData
import retrofit2.Response

class ServerRequestsImpl() : ServerRequests {
    override suspend fun LoginWithCredentials(): Response<ResponseData> {
        TODO("Not yet implemented")
    }

    override fun LoginWithJwt() {
        TODO("Not yet implemented")
    }

}