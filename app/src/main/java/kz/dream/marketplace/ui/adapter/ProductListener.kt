package kz.dream.marketplace.ui.adapter

import kz.dream.marketplace.entity.ItemEntity

interface ProductListener {
    fun onProductClick(product: ItemEntity)
    fun onFavButtonClick(product: ItemEntity)
}