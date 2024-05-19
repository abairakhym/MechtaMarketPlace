package kz.dream.marketplace

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.dream.marketplace.entity.ItemEntity

interface MechtaRepository {
    suspend fun getCatalog(): Flow<PagingData<ItemEntity>>
    suspend fun addToFavorites(product: ItemEntity)
    suspend fun getFavoriteProducts(): List<ItemEntity>
    suspend fun removeFromFavorites(product: ItemEntity)
}