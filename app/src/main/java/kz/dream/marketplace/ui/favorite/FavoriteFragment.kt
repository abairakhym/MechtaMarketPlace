package kz.dream.marketplace.ui.favorite

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kz.dream.marketplace.R
import kz.dream.marketplace.common.invisible
import kz.dream.marketplace.common.visible
import kz.dream.marketplace.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kz.dream.marketplace.common.viewBinding
import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.ui.adapter.ProductListener
import kz.dream.marketplace.ui.MechtaState
import kz.dream.marketplace.ui.adapter.ProductsAdapter

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite), ProductListener {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel by viewModels<FavoriteViewModel>()
    private val adapter by lazy { ProductsAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteProducts()
        with(binding) { rvFavorites.adapter = adapter }
        observeData()
    }

    override fun onProductClick(product: ItemEntity) {
        val action = FavoriteFragmentDirections.favoriteToDetail(product)
        findNavController().navigate(action)
    }

    override fun onFavButtonClick(product: ItemEntity) {
        viewModel.removeFromFavorites(product)
        viewModel.getFavoriteProducts()
    }

    private fun observeData() = with(binding) {
        viewModel.favoriteState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MechtaState.Loading -> {
                    pbFavorite.visible()
                }

                is MechtaState.Success -> {
                    rvFavorites.visible()
                    pbFavorite.invisible()
                    adapter.submitList(state.products)
                }

                is MechtaState.Error -> {
                    rvFavorites.invisible()
                    pbFavorite.invisible()
                    Toast.makeText(requireContext(), state.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}