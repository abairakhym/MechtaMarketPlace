package kz.dream.marketplace.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.dream.marketplace.model.local.ItemLocalDataSource

@Dao
interface MechtaDao {

    @Query("SELECT * FROM mechta_sf")
    suspend fun getFavoriteProducts(): List<ItemLocalDataSource>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(product: ItemLocalDataSource)

    @Delete
    suspend fun removeFromFavorites(product: ItemLocalDataSource)
}