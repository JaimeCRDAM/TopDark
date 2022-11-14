package Models.dataclasses.user

data class ResponseData(
    val `data`: User? = User(),
    val message: String? = null,
    val statusCode: StatusCode = StatusCode()
)