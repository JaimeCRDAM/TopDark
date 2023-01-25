package models.data_classes.all_ships_response

import models.data_classes.response_data.StatusCode

data class AllShipsResponse(
    val `data`: List<Data?>? = null,
    val message: Any? = null,
    val statusCode: StatusCode? = null
)