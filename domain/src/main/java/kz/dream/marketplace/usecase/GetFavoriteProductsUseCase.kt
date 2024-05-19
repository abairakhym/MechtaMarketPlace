package kz.dream.marketplace.usecase

import kz.dream.marketplace.MechtaRepository

class GetFavoriteProductsUseCase(private val repository: MechtaRepository) {
    suspend operator fun invoke() = repository.getFavoriteProducts()
}