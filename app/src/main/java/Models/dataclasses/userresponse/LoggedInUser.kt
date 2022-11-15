package Models.dataclasses.userresponse

data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val authToken: String
)