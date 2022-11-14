package Models.dataclasses.errorbody

data class ErrorCode(
    val `data`: Any? = Any(),
    val message: String? = "",
    val statusCode: StatusCodeX? = StatusCodeX()
)