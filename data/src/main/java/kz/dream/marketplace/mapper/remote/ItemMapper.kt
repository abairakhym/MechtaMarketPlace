package kz.dream.marketplace.mapper.remote

import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.mapper.Mapper
import kz.dream.marketplace.model.ItemNetworkDataSource

class ItemMapper: Mapper<ItemEntity, ItemNetworkDataSource> {
    override fun mapFromEntity(type: ItemEntity) = ItemNetworkDataSource(
        id = type.id, name = type.name, photos = type.photos.map { it }, title = type.title//, isFavorite = type.isFavorite
    )

    override fun mapToEntity(type: ItemNetworkDataSource) = ItemEntity(
        id = type.id, name = type.name, photos = type.photos.map { it }, title = type.title//, isFavorite = type.isFavorite
    )
}