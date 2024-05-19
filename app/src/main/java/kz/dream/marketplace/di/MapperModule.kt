package kz.dream.marketplace.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.dream.marketplace.mapper.local.ItemLocalMapper
import kz.dream.marketplace.mapper.remote.DataMapper
import kz.dream.marketplace.mapper.remote.ItemMapper
import kz.dream.marketplace.mapper.remote.MechtaMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun providesItemMapper(): ItemMapper = ItemMapper()

    @Provides
    @Singleton
    fun providesItemLocalMapper(): ItemLocalMapper = ItemLocalMapper()

    @Provides
    @Singleton
    fun providesMechtaMapper(dataMapper: DataMapper): MechtaMapper = MechtaMapper(mapper = dataMapper)

    @Provides
    @Singleton
    fun providesDataMapper(itemMapper: ItemMapper): DataMapper = DataMapper(mapper = itemMapper)
}