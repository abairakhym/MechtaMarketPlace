package kz.dream.marketplace.usecase

import kz.dream.marketplace.MechtaRepository
import kz.dream.marketplace.entity.ItemEntity

class AddToFavoritesUseCase(private val repository: MechtaRepository) {
    suspend operator fun invoke(product: ItemEntity) = repository.addToFavorites(product = product)
}