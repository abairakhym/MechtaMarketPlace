package kz.dream.marketplace.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kz.dream.marketplace.R
import kz.dream.marketplace.common.loadImage
import kz.dream.marketplace.common.viewBinding
import kz.dream.marketplace.databinding.FragmentDetailBinding
import kz.dream.marketplace.ui.home.HomeViewModel

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by viewModels<HomeViewModel>()
    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.item

        with(binding) {
            tvProductTitle.text = product.title
            ivProduct.loadImage(product.photos.first())
            if (product.isFavorite) ivFavorite.setImageResource(R.drawable.ic_favorite_filled)

            ivFavorite.setOnClickListener {
                if (product.isFavorite) {
                    product.isFavorite = false
                    viewModel.removeFromFavorite(product = product)
                    ivFavorite.setImageResource(R.drawable.ic_favorite)
                } else {
                    product.isFavorite = true
                    viewModel.addToFavorite(product = product)
                    ivFavorite.setImageResource(R.drawable.ic_favorite_filled)
                }
            }
        }
    }
}
