package Models.dataclasses.user

data class ResponseData(
    val `data`: Data? = null,
    val message: Any? = null,
    val statusCode: StatusCode? = null
)