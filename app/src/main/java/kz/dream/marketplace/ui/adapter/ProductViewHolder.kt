package kz.dream.marketplace.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import kz.dream.marketplace.R
import kz.dream.marketplace.common.loadImage
import kz.dream.marketplace.databinding.ProductItemBinding
import kz.dream.marketplace.entity.ItemEntity

class ProductViewHolder(
    private val binding: ProductItemBinding,
    private val productListener: ProductListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ItemEntity) = with(binding) {
        tvProductTitle.text = product.title
        ivProduct.loadImage(product.photos.first())

        if (product.isFavorite) {
            ivFavorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            ivFavorite.setImageResource(R.drawable.ic_favorite)
        }

        ivFavorite.setOnClickListener {
            if (product.isFavorite) {
                product.isFavorite = false
                ivFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                product.isFavorite = true
                ivFavorite.setImageResource(R.drawable.ic_favorite_filled)
            }
            productListener.onFavButtonClick(product)
        }

        ivProduct.setOnClickListener { productListener.onProductClick(product) }
    }
}