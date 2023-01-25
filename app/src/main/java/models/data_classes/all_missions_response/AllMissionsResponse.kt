package models.data_classes.all_missions_response

import models.data_classes.response_data.StatusCode

data class AllMissionsResponse(
    val `data`: List<Data>? = listOf(),
    val message: Any? = Any(),
    val statusCode: StatusCode? = StatusCode()
)