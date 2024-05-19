package kz.dream.marketplace.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.dream.marketplace.db.MechtaDao
import kz.dream.marketplace.MechtaRepository
import kz.dream.marketplace.MechtaService
import kz.dream.marketplace.mapper.local.ItemLocalMapper
import kz.dream.marketplace.mapper.remote.ItemMapper
import kz.dream.marketplace.repository.MechtaRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesMechtaRepository(
        service: MechtaService,
        dao: MechtaDao,
        itemMapper: ItemMapper,
        itemLocalMapper: ItemLocalMapper
    ): MechtaRepository = MechtaRepositoryImpl(
        service = service,
        dao = dao,
        itemMapper = itemMapper,
        itemLocalMapper = itemLocalMapper
    )
}