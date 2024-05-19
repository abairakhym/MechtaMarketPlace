package kz.dream.marketplace.model

data class ItemNetworkDataSource(
    val id: Int,
    val name: String,
    val photos: List<String>,
    val title: String,
)