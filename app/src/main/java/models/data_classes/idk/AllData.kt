package models.data_classes.idk

data class AllData(
    val `data`: List<Data?>? = null,
    val message: String? = null,
    val statusCode: StatusCode? = null
)