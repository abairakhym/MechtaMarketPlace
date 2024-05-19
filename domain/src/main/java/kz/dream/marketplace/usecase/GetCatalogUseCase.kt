package kz.dream.marketplace.usecase

import kz.dream.marketplace.MechtaRepository

class GetCatalogUseCase(private val repository: MechtaRepository) {
    suspend operator fun invoke() = repository.getCatalog()
}