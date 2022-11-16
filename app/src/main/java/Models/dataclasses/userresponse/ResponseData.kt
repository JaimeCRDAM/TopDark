package Models.dataclasses.userresponse

data class ResponseData(
    val `data`: Data? = null,
    val message: String? = null,
    val statusCode: StatusCode? = null
)