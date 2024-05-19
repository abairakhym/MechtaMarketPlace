package kz.dream.marketplace.mapper

interface Mapper<E, N> {
    fun mapFromEntity(type: E): N
    fun mapToEntity(type: N): E
}