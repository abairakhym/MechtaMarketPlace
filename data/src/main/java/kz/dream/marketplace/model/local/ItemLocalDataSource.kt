package kz.dream.marketplace.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mechta_sf")
data class ItemLocalDataSource(
    @PrimaryKey
    val id: Int,
    val name: String,
    val photos: String,
    val title: String,
    val isFavorite: Boolean
)