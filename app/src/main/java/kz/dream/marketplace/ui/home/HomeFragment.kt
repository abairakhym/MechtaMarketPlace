package kz.dream.marketplace.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import kz.dream.marketplace.R
import kz.dream.marketplace.common.invisible
import kz.dream.marketplace.common.visible
import kz.dream.marketplace.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.dream.marketplace.common.viewBinding
import kz.dream.marketplace.entity.ItemEntity
import kz.dream.marketplace.ui.adapter.ProductListener
import kz.dream.marketplace.ui.adapter.ProductsPaginationAdapter

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ProductListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { ProductsPaginationAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProducts()
        setUI()
        lifecycleScope.launch { viewModel.catalog.collectLatest(adapter::submitData) }
    }

    override fun onProductClick(product: ItemEntity) {
        val direction = HomeFragmentDirections.homeToDetail(product)
        findNavController().navigate(direction)
    }

    override fun onFavButtonClick(product: ItemEntity) {
        if (product.isFavorite) {
            viewModel.addToFavorite(product)
        } else {
            viewModel.removeFromFavorite(product)
        }
    }

    private fun setUI() = with(binding) {
        rvProducts.adapter = adapter

        adapter.addLoadStateListener {
            when {
                it.refresh == LoadState.Loading -> pbHome.visible()
                it.append == LoadState.Loading -> pbHome.visible()
                else -> pbHome.invisible()
            }
        }
    }
}