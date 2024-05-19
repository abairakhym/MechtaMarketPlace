package kz.dream.marketplace.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kz.dream.marketplace.MechtaRepository
import kz.dream.marketplace.usecase.AddToFavoritesUseCase
import kz.dream.marketplace.usecase.GetCatalogUseCase
import kz.dream.marketplace.usecase.GetFavoriteProductsUseCase
import kz.dream.marketplace.usecase.RemoveFromFavoritesUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesAddToFavoritesUseCase(repository: MechtaRepository): AddToFavoritesUseCase =
        AddToFavoritesUseCase(repository)

    @Provides
    @Singleton
    fun providesGetFavoriteProductsUseCase(repository: MechtaRepository): GetFavoriteProductsUseCase =
        GetFavoriteProductsUseCase(repository)

    @Provides
    @Singleton
    fun providesGetCatalogUseCase(repository: MechtaRepository): GetCatalogUseCase =
        GetCatalogUseCase(repository)

    @Provides
    @Singleton
    fun providesRemoveFromFavoritesUseCase(repository: MechtaRepository): RemoveFromFavoritesUseCase =
        RemoveFromFavoritesUseCase(repository)
}