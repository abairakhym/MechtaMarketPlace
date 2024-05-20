package kz.dream.marketplace.mapper.local

import kz.dream.marketplace.model.local.ItemLocalDataSource
import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.mapper.Mapper

class ItemLocalMapper: Mapper<ItemEntity, ItemLocalDataSource> {
    override fun mapFromEntity(type: ItemEntity) = ItemLocalDataSource(
        id = type.id, name = type.name, photos = type.photos.first(), title = type.title, isFavorite = type.isFavorite
    )

    override fun mapToEntity(type: ItemLocalDataSource) = ItemEntity(
        id = type.id, name = type.name, photos = listOf(type.photos), title = type.title, isFavorite = type.isFavorite
    )
}