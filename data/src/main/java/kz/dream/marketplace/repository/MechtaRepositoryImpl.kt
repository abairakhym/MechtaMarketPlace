package kz.dream.marketplace.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.dream.marketplace.CatalogPagingSource
import kz.dream.marketplace.MechtaRepository
import kz.dream.marketplace.db.MechtaDao
import kz.dream.marketplace.MechtaService
import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.mapper.local.ItemLocalMapper
import kz.dream.marketplace.mapper.remote.ItemMapper

class MechtaRepositoryImpl(
    private val service: MechtaService,
    private val dao: MechtaDao,
    private val itemMapper: ItemMapper,
    private val itemLocalMapper: ItemLocalMapper
) : MechtaRepository {

    override suspend fun getCatalog(): Flow<PagingData<ItemEntity>> =
        Pager(PagingConfig(pageSize = 25, enablePlaceholders = false, initialLoadSize = 4)) {
            CatalogPagingSource(service = service, mapper = itemMapper, dao = dao)
        }.flow

    override suspend fun addToFavorites(product: ItemEntity) {
        dao.addToFavorites(product = itemLocalMapper.mapFromEntity(product))
    }

    override suspend fun getFavoriteProducts(): List<ItemEntity> {
        val products = mutableListOf<ItemEntity>()
        dao.getFavoriteProducts().forEach {
            products.add(itemLocalMapper.mapToEntity(it))
        }
        return products
    }

    override suspend fun removeFromFavorites(product: ItemEntity) {
        dao.removeFromFavorites(product = itemLocalMapper.mapFromEntity(product))
    }
}