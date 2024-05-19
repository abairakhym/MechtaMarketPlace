package kz.dream.marketplace.mapper.remote

import kz.dream.marketplace.entity.MechtaEntity
import kz.dream.marketplace.mapper.Mapper
import kz.dream.marketplace.model.MechtaNetworkDataSource

class MechtaMapper(private val mapper: DataMapper): Mapper<MechtaEntity, MechtaNetworkDataSource> {
    override fun mapFromEntity(type: MechtaEntity) = MechtaNetworkDataSource(
        data = mapper.mapFromEntity(type.data), result = type.result
    )

    override fun mapToEntity(type: MechtaNetworkDataSource) = MechtaEntity(
        data = mapper.mapToEntity(type.data), result = type.result
    )
}