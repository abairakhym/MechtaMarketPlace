package kz.dream.marketplace

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kz.dream.marketplace.db.MechtaDao
import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.mapper.remote.ItemMapper

class CatalogPagingSource(
    private val service: MechtaService,
    private val mapper: ItemMapper,
    private val dao: MechtaDao,
): PagingSource<Int, ItemEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ItemEntity>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemEntity> {
        val page = params.key ?: 1
        val result = service.getCatalogFromApi(page = page).data.items
        val favorites = dao.getFavoriteProducts()
        val catalog = result.map { mapper.mapToEntity(it) }

        favorites.forEach { favoritesItem ->
            catalog.forEach { if (favoritesItem.id == it.id) it.isFavorite = true }
        }

        return LoadResult.Page(
            data = catalog,
            prevKey = if (page >= 1) page - 1 else null,
            nextKey = if (result.isNotEmpty()) page.plus(1) else null,
        )
    }
}
