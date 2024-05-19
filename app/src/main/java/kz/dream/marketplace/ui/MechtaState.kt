package kz.dream.marketplace.ui

import kz.dream.marketplace.entity.ItemEntity

sealed interface MechtaState {
    object Loading : MechtaState
    data class Success(val products: List<ItemEntity>) : MechtaState
    data class Error(val msg: String) : MechtaState
}
