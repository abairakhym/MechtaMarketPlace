package kz.dream.marketplace.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kz.dream.marketplace.databinding.ProductItemBinding
import kz.dream.marketplace.entity.ItemEntity

class ProductsAdapter(private val productListener: ProductListener) :
    ListAdapter<ItemEntity, ProductViewHolder>(DiscountProductDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder = ProductViewHolder(
        ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), productListener
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) = holder.bind(getItem(position))
}



