package Models.dataclasses.user

data class ResponseData(
    val `data`: User = User(),
    val message: Any? = Any(),
    val statusCode: StatusCode = StatusCode()
)