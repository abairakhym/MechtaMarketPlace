package kz.dream.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import kz.dream.marketplace.databinding.ProductItemBinding
import kz.dream.marketplace.entity.ItemEntity

class ProductsPaginationAdapter(private val productListener: ProductListener) :
    PagingDataAdapter<ItemEntity, ProductViewHolder>(DiscountProductDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder = ProductViewHolder(
        ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), productListener
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}