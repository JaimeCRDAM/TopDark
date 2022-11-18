package models.data_classes.response_data

import models.data_classes.user_login_response.Data

data class ResponseData(
    val `data`: Data? = null,
    val message: String? = null,
    val statusCode: StatusCode? = null
)