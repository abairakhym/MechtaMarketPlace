package kz.dream.marketplace.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import kz.dream.marketplace.entity.ItemEntity

class DiscountProductDiffCallBack : DiffUtil.ItemCallback<ItemEntity>() {
    override fun areContentsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
        return oldItem.id == newItem.id
    }
}