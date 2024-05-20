package kz.dream.marketplace.mapper.remote

import kz.dream.marketplace.entity.DataEntity
import kz.dream.marketplace.mapper.Mapper
import kz.dream.marketplace.model.remote.DataNetworkDataSource

class DataMapper(private val mapper: ItemMapper): Mapper<DataEntity, DataNetworkDataSource> {
    override fun mapFromEntity(type: DataEntity) = DataNetworkDataSource(
        items = type.items.map { mapper.mapFromEntity(it) }
    )

    override fun mapToEntity(type: DataNetworkDataSource) = DataEntity(
        items = type.items.map { mapper.mapToEntity(it) }
    )
}