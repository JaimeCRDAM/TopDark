package models.data_classes.all_pilots_response

import models.data_classes.response_data.StatusCode

data class AllPilotsResponse(
    val `data`: List<Data?>? = null,
    val message: Any? = null,
    val statusCode: StatusCode? = null
)